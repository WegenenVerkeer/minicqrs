package be.wegenenverkeer.minicqrs.core;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.function.ThrowingFunction;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import be.wegenenverkeer.minicqrs.core.journal.JournalEntity;
import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionManager;
import be.wegenenverkeer.minicqrs.core.snapshot.SnapshotEntity;
import be.wegenenverkeer.minicqrs.core.snapshot.SnapshotRepository;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.BaseEvent;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public abstract class AbstractAggregateBehaviour<ID, S, C, E> {
  private static Logger LOG = LoggerFactory.getLogger(AbstractAggregateBehaviour.class);

  private Cache<String, StateHolder<S>> cache;
  private ProjectionManager projectionManager;
  private JournalRepository<E> journalRepository;
  private SnapshotRepository<S> snapshotRepository;
  private ObjectMapper objectMapper;
  private JavaType eventType;
  private JavaType stateType;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public AbstractAggregateBehaviour(ObjectMapper objectMapper,
      CacheManager cacheManager,
      ProjectionManager projectionManager,
      JournalRepository<E> journalRepository,
      SnapshotRepository<S> snapshotRepository,
      Class<E> eventClass, Class<S> stateClass) {
    this.projectionManager = projectionManager;
    this.eventType = TypeFactory.defaultInstance().constructType(eventClass);
    this.stateType = TypeFactory.defaultInstance().constructType(stateClass);
    this.objectMapper = objectMapper;
    this.journalRepository = journalRepository;
    this.snapshotRepository = snapshotRepository;
    this.cache = (Cache<String, StateHolder<S>>) (Cache) cacheManager
        .createCache(BaseEvent.class.getCanonicalName(),
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, StateHolder.class,
                    ResourcePoolsBuilder.heap(100)) // TODO: configure
                .build());
  }

  private static record StateAndEventHolder<S, E>(StateHolder<S> state, List<EventHolder<E>> events) {
  }

  public Mono<List<E>> processCommand(ID id, C command) {
    LOG.info("processCommand");
    String internalId = fromId(id);
    // 1: get state + sequence from in-memory or snapshot
    return getStateFromMemory(internalId)
        .flatMap(maybeMemoryState -> maybeMemoryState.map(memoryState -> Mono.just(Optional.of(memoryState)))
            // 2: if not exists, get state + sequence from snapshots
            .orElseGet(() -> getLatestSnapshot(internalId)))
        // 3: get events from journal with sequence > last sequence
        .flatMap(maybeState -> getEventsSince(internalId, maybeState.map(state -> state.sequence()).orElse(0L))
            // 4: apply events tot state
            .map(events -> applyEvents(id, maybeState.orElse(emptyStateHolder(internalId)), events))
            // 5: produce events from command and state
            .map(state -> applyCommandToState(id, state, command)))
        // 6: save events in journal
        .flatMap(stateAndEvents -> saveEvents(stateAndEvents.events()).map(_ignore -> stateAndEvents))
        // 7: if needed, save snapshot
        .flatMap(stateAndEvents -> saveSnapshot(stateAndEvents.state()).map(_ignore -> stateAndEvents))
        // 8: save state + sequence in memory
        .map(stateAndEvents -> {
          saveStateInMemory(stateAndEvents.state());
          projectionManager.triggerProjections();
          return stateAndEvents.events().stream().map(e -> e.event()).collect(Collectors.toList());
        })
        // 9: retry when we get a duplicatekey exception
        .retryWhen(
            Retry.backoff(10, Duration.ofMillis(100)) // TODO: configure
                .filter(t -> t instanceof DuplicateKeyException));
  }

  private Mono<List<EventHolder<E>>> getEventsSince(String id, long since) {
    LOG.info("getEventsSince on " + id + " since " + since);
    return journalRepository.findByIdAndTypeAndSequenceGreaterThanOrderBySequence(id, eventType.toCanonical(), since)
        .map(ThrowingFunction.of(entity -> entity.toHolder(objectMapper, eventType))).collectList();
  }

  private List<JournalEntity<E>> toJournal(long shard, List<EventHolder<E>> events) {
    return events.stream().map(event -> new JournalEntity<E>(objectMapper, event, eventType.toCanonical(), shard))
        .toList();
  }

  private Mono<Integer> saveEvents(List<EventHolder<E>> events) {
    if (events.isEmpty()) {
      return Mono.just(0);
    } else {
      String id = events.getFirst().id();
      long shard = getShard(toId(id));
      List<JournalEntity<E>> journalEvents = toJournal(shard, events);
      return journalRepository.saveAll(journalEvents).collectList().map(r -> r.size());
    }
  }

  private Mono<Optional<StateHolder<S>>> getStateFromMemory(String id) {
    return Mono.fromCallable(() -> {
      var state = Optional.ofNullable(cache.get(id));
      LOG.info("getStateFromMemory " + state.map(s -> Long.toString(s.sequence())).orElse(" not found "));
      return state;
    });
  }

  private void saveStateInMemory(StateHolder<S> state) {
    cache.put(state.id(), state);
  }

  private Mono<Optional<StateHolder<S>>> getLatestSnapshot(String id) {
    LOG.info("getLatestSnapshot");
    return snapshotRepository.findByIdAndType(id, stateType.toCanonical())
        .map(ThrowingFunction.of(entity -> entity.toHolder(objectMapper, stateType)))
        .map(Optional::of).defaultIfEmpty(Optional.empty());
  }

  private Mono<Integer> saveSnapshot(StateHolder<S> state) {
    if (state.sequence() % 10 == 0) {
      return snapshotRepository.replace(new SnapshotEntity<S>(objectMapper, state, stateType.toCanonical()),
          stateType.toCanonical());
    } else {
      return Mono.just(0);
    }
  }

  private StateHolder<S> emptyStateHolder(String id) {
    return new StateHolder<>(id, emptyState(), 0L);
  }

  private StateHolder<S> applyEvents(ID id, StateHolder<S> state, List<EventHolder<E>> events) {
    for (EventHolder<E> e : events) {
      state = state.advance(applyEvent(id, state.state(), e.event()));
    }
    return state;
  }

  private StateAndEventHolder<S, E> applyCommandToState(ID id, StateHolder<S> state, C command) {
    LOG.info("applyCommandToState " + Thread.currentThread().getName());
    // LOG.info("applyCommandToState ");
    List<E> events = applyCommand(id, state.state(), command);
    List<EventHolder<E>> eventHolders = new ArrayList<>(events.size());
    Instant occured = Instant.now();
    for (E e : events) {
      state = state.advance(applyEvent(id, state.state(), e));
      eventHolders.add(new EventHolder<>(state.id(), e, state.sequence(), occured));
    }
    return new StateAndEventHolder<>(state, eventHolders);

  }

  protected abstract S applyEvent(ID id, S state, E event);

  protected abstract List<E> applyCommand(ID id, S state, C command);

  protected abstract S emptyState();

  protected abstract long getShard(ID id);

  protected abstract ID toId(String id);

  protected String fromId(ID id) {
    return id.toString();
  }
}
