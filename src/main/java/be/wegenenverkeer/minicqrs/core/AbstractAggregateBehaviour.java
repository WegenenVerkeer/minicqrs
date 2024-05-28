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

  private Cache<ID, StateHolder<ID, S>> cache;
  private ProjectionManager projectionManager;
  private JournalRepository<ID, E> journalRepository;
  private SnapshotRepository<ID, S> snapshotRepository;
  private ObjectMapper objectMapper;
  private JavaType eventType;
  private JavaType stateType;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public AbstractAggregateBehaviour(ObjectMapper objectMapper,
      CacheManager cacheManager,
      ProjectionManager projectionManager,
      JournalRepository<ID, E> journalRepository,
      SnapshotRepository<ID, S> snapshotRepository,
      Class<E> eventClass, Class<ID> idClass, Class<S> stateClass) {
    this.projectionManager = projectionManager;
    this.eventType = TypeFactory.defaultInstance().constructType(eventClass);
    this.stateType = TypeFactory.defaultInstance().constructType(stateClass);
    this.objectMapper = objectMapper;
    this.journalRepository = journalRepository;
    this.snapshotRepository = snapshotRepository;
    this.cache = (Cache<ID, StateHolder<ID, S>>) (Cache) cacheManager
        .createCache(BaseEvent.class.getCanonicalName(),
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(idClass, StateHolder.class,
                    ResourcePoolsBuilder.heap(100)) // TODO: configure
                .build());
  }

  private static record StateAndEventHolder<ID, S, E>(StateHolder<ID, S> state, List<EventHolder<ID, E>> events) {
  }

  public Mono<List<E>> processCommand(ID id, C command) {
    LOG.info("processCommand");
    // 1: get state + sequence from in-memory or snapshot
    return getStateFromMemory(id)
        .flatMap(maybeMemoryState -> maybeMemoryState.map(memoryState -> Mono.just(Optional.of(memoryState)))
            // 2: if not exists, get state + sequence from snapshots
            .orElseGet(() -> getLatestSnapshot(id)))
        // 3: get events from journal with sequence > last sequence
        .flatMap(maybeState -> getEventsSince(id, maybeState.map(state -> state.sequence()).orElse(0L))
            // 4: apply events tot state
            .map(events -> applyEvents(maybeState.orElse(emptyStateHolder(id)), events))
            // 5: produce events from command and state
            .map(state -> applyCommandToState(state, command)))
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

  private Mono<List<EventHolder<ID, E>>> getEventsSince(ID id, long since) {
    LOG.info("getEventsSince on " + id + " since " + since);
    return journalRepository.findByIdAndTypeAndSequenceGreaterThanOrderBySequence(id, eventType.toCanonical(), since)
        .map(ThrowingFunction.of(entity -> entity.toHolder(objectMapper, eventType))).collectList();
  }

  private List<JournalEntity<ID, E>> toJournal(long shard, List<EventHolder<ID, E>> events) {
    return events.stream().map(event -> new JournalEntity<ID, E>(objectMapper, event, eventType.toCanonical(), shard))
        .toList();
  }

  private Mono<Integer> saveEvents(List<EventHolder<ID, E>> events) {
    if (events.isEmpty()) {
      return Mono.just(0);
    } else {
      ID id = events.getFirst().id();
      long shard = getShard(id);
      List<JournalEntity<ID, E>> journalEvents = toJournal(shard, events);
      return journalRepository.saveAll(journalEvents).collectList().map(r -> r.size());
    }
  }

  private Mono<Optional<StateHolder<ID, S>>> getStateFromMemory(ID id) {
    return Mono.fromCallable(() -> {
      var state = Optional.ofNullable(cache.get(id));
      LOG.info("getStateFromMemory " + state.map(s -> Long.toString(s.sequence())).orElse(" not found "));
      return state;
    });
  }

  private void saveStateInMemory(StateHolder<ID, S> state) {
    cache.put(state.id(), state);
  }

  private Mono<Optional<StateHolder<ID, S>>> getLatestSnapshot(ID id) {
    LOG.info("getLatestSnapshot");
    return snapshotRepository.findByIdAndType(id, stateType.toCanonical())
        .map(ThrowingFunction.of(entity -> entity.toHolder(objectMapper, stateType)))
        .map(Optional::of).defaultIfEmpty(Optional.empty());
  }

  private Mono<Integer> saveSnapshot(StateHolder<ID, S> state) {
    if (state.sequence() % 10 == 0) {
      return snapshotRepository.replace(new SnapshotEntity<ID, S>(objectMapper, state, stateType.toCanonical()),
          stateType.toCanonical());
    } else {
      return Mono.just(0);
    }
  }

  private StateHolder<ID, S> emptyStateHolder(ID id) {
    return new StateHolder<>(id, emptyState(), 0L);
  }

  private StateHolder<ID, S> applyEvents(StateHolder<ID, S> state, List<EventHolder<ID, E>> events) {
    for (EventHolder<ID, E> e : events) {
      state = state.advance(applyEvent(state.state(), e.event()));
    }
    return state;
  }

  private StateAndEventHolder<ID, S, E> applyCommandToState(StateHolder<ID, S> state,
      C command) {
    LOG.info("applyCommandToState " + Thread.currentThread().getName());
    // LOG.info("applyCommandToState ");
    var events = applyCommand(state.state(), command);
    List<EventHolder<ID, E>> eventHolders = new ArrayList<>(events.size());
    Instant occured = Instant.now();
    for (E e : events) {
      state = state.advance(applyEvent(state.state(), e));
      eventHolders.add(new EventHolder<>(state.id(), e, state.sequence(), occured));
    }
    return new StateAndEventHolder<>(state, eventHolders);

  }

  protected abstract S applyEvent(S state, E event);

  protected abstract List<E> applyCommand(S state, C command);

  protected abstract S emptyState();

  protected abstract long getShard(ID id);
}
