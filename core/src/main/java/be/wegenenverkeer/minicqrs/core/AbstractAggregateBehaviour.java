package be.wegenenverkeer.minicqrs.core;

import be.wegenenverkeer.minicqrs.core.JournalRepository.EventHolder;
import be.wegenenverkeer.minicqrs.core.SnapshotRepository.StateHolder;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionManager;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionOffset;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.time.Duration;
import java.time.LocalDateTime;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.comparator.Comparators;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

public abstract class AbstractAggregateBehaviour<ID, S, C, E> {
  private static Logger LOG = LoggerFactory.getLogger(AbstractAggregateBehaviour.class);

  private final Cache<String, StateHolder<S>> cache;
  @Autowired private ProjectionManager projectionManager;
  private final JavaType eventType;
  private final JavaType stateType;

  @Autowired private JournalRepository<E> journalRepository;

  @Autowired private SnapshotRepository<S> snapshotRepository;

  @Autowired protected TransactionalOperator rxtx;

  @SuppressWarnings({"rawtypes", "unchecked"})
  public AbstractAggregateBehaviour(
      CacheManager cacheManager, Class<E> eventClass, Class<S> stateClass) {
    this.eventType = TypeFactory.defaultInstance().constructType(eventClass);
    this.stateType = TypeFactory.defaultInstance().constructType(stateClass);
    this.cache =
        (Cache<String, StateHolder<S>>)
            (Cache)
                cacheManager.createCache(
                    stateType.toCanonical(),
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(
                            String.class,
                            StateHolder.class,
                            ResourcePoolsBuilder.heap(getCacheSize()))
                        .build());
  }

  private static record StateAndEventHolder<S, E>(
      StateHolder<S> state, List<EventHolder<E>> events) {
    public StateAndEventHolder<S, E> withEvents(List<EventHolder<E>> events) {
      return new StateAndEventHolder<>(state, events);
    }
  }

  // Process command and return the events with their info.
  public Mono<List<EventHolder<E>>> processCommandGetEventHolders(ID id, C command) {
    LOG.info("processCommand");
    String internalId = fromId(id);
    // 1: get state + sequence from in-memory or snapshot
    return getStateFromMemory(internalId)
        .flatMap(
            maybeMemoryState ->
                maybeMemoryState
                    .map(memoryState -> Mono.just(Optional.of(memoryState)))
                    // 2: if not exists, get state + sequence from snapshots
                    .orElseGet(() -> snapshotRepository.getLatestSnapshot(internalId, stateType)))
        // 3: get events from journal with sequence > last sequence
        .flatMap(
            maybeState ->
                journalRepository
                    .getEventsSince(
                        internalId, maybeState.map(state -> state.sequence()).orElse(0L), eventType)
                    // 4: apply events tot state
                    .map(
                        events ->
                            applyEvents(
                                id, maybeState.orElse(emptyStateHolder(internalId)), events))
                    // 5: produce events from command and state
                    .map(state -> applyCommandToState(id, state, command)))
        // 6: save events in journal
        .flatMap(
            stateAndEvents ->
                journalRepository
                    .saveEvents(stateAndEvents.events())
                    .map(events -> stateAndEvents.withEvents(events)))
        // 7: if needed, save snapshot
        .flatMap(
            stateAndEvents -> saveSnapshot(stateAndEvents.state()).map(_ignore -> stateAndEvents))
        // 8: save state + sequence in memory
        .map(
            stateAndEvents -> {
              saveStateInMemory(stateAndEvents.state());
              projectionManager.triggerProjections();
              return stateAndEvents.events();
            })
        // 9: retry when we get a duplicatekey exception
        .retryWhen(
            getRetryBackoffSpec()
                .filter(
                    t -> t instanceof org.jooq.exception.IntegrityConstraintViolationException));
  }

  // Process command and return the events
  public Mono<List<E>> processCommandGetEvents(ID id, C command) {
    return processCommand(id, command).map(Pair::getSecond);
  }

  // Process command and return the highest offset
  public Mono<ProjectionOffset> processCommandGetOffset(ID id, C command) {
    return processCommand(id, command).map(Pair::getFirst);
  }

  // Process command and return both the highest offset and resultant events
  public Mono<Pair<ProjectionOffset, List<E>>> processCommand(ID id, C command) {
    return processCommandGetEventHolders(id, command)
        .flatMap(
            events ->
                events.stream()
                    .map(e -> e.globalSequence())
                    .max(Comparators.comparable())
                    .map(
                        v ->
                            Mono.just(
                                Pair.of(
                                    new ProjectionOffset(getShard(id), v),
                                    events.stream()
                                        .map(e -> e.event())
                                        .collect(Collectors.toList()))))
                    .orElse(Mono.empty()));
  }

  @FunctionalInterface
  public interface EventToCommandGenerator<ID, C, E> {
    Optional<Pair<ID, C>> command(E event);
  }

  // Process command met extra optie om vanuit de gegenereerde events commands uit te voeren. De uit
  // te voeren commands worden
  // bepaald door de EventToCommandGenerator
  public Mono<Pair<ProjectionOffset, List<E>>> processCommand(
      ID id, C command, EventToCommandGenerator<ID, C, E> eventToCommand) {
    return rxtx.transactional(
        processCommand(id, command)
            .flatMap(
                result ->
                    Flux.fromIterable(result.getSecond())
                        .map(eventToCommand::command)
                        .flatMap(
                            maybeCommand ->
                                maybeCommand
                                    .map(pair -> processCommand(pair.getFirst(), pair.getSecond()))
                                    .orElse(Mono.empty()))
                        .switchIfEmpty(
                            Mono.just(
                                result)) // als er geen commands verwerkt werden, stuur resultaat
                        // van oorspronkelijk command
                        .last()));
  }

  private Mono<Optional<StateHolder<S>>> getStateFromMemory(String id) {
    return Mono.fromCallable(
        () -> {
          var state = Optional.ofNullable(cache.get(id));
          LOG.info(
              "getStateFromMemory "
                  + state.map(s -> Long.toString(s.sequence())).orElse(" not found "));
          return state;
        });
  }

  private void saveStateInMemory(StateHolder<S> state) {
    cache.put(state.id(), state);
  }

  private Mono<Integer> saveSnapshot(StateHolder<S> state) {
    if (state.sequence() % getSnapshotTreshhold() == 0) {
      try {
        return snapshotRepository.saveSnapshot(state);
      } catch (Exception e) {
        LOG.warn("Cannot convert state to json, skipping snapshot", e);
      }
    }
    return Mono.just(0);
  }

  private StateHolder<S> emptyStateHolder(String id) {
    return new StateHolder<>(id, emptyState(), 0L, stateType);
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
    LocalDateTime occured = LocalDateTime.now();
    long shard = getShard(id);
    for (E e : events) {
      state = state.advance(applyEvent(id, state.state(), e));
      eventHolders.add(
          new EventHolder<>(state.id(), shard, e, state.sequence(), null, occured, eventType));
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

  protected int getCacheSize() {
    return 100;
  }

  protected int getSnapshotTreshhold() {
    return 10;
  }

  protected RetryBackoffSpec getRetryBackoffSpec() {
    return Retry.backoff(10, Duration.ofMillis(100));
  }
}
