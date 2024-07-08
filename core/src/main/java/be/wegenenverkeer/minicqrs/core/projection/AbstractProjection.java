package be.wegenenverkeer.minicqrs.core.projection;

import be.wegenenverkeer.minicqrs.core.JournalRepository;
import be.wegenenverkeer.minicqrs.core.JournalRepository.EventHolder;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.time.Duration;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehcache.Cache;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.util.Pair;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public abstract class AbstractProjection<ID, E> {
  private static Logger LOG = LoggerFactory.getLogger(AbstractProjection.class);

  public static record ProjectionId(String projection, long shard) {}

  private final Cache<ProjectionId, Long> cache;
  private final Set<Long> shards;
  private final JavaType eventType;

  @Autowired private JournalRepository<E> journalRepository;

  @Autowired private ProjectionOffsetRepository projectionOffsetRepository;

  private final Sinks.Many<Long> trigger;

  @Autowired protected TransactionalOperator rxtx;

  @Autowired protected DSLContext ctx;

  public AbstractProjection(
      Cache<ProjectionId, Long> cache, Set<Long> shards, Class<E> eventClass) {
    this.eventType = TypeFactory.defaultInstance().constructType(eventClass);
    this.cache = cache;
    this.shards = shards;
    this.trigger = Sinks.many().replay().latest();
  }

  public Flux<Void> start() {
    triggerProjection();
    Scheduler scheduler = Schedulers.newParallel(getProjectionName(), 1);
    return trigger
        .asFlux()
        .sampleTimeout(
            u -> Mono.empty().delaySubscription(Duration.ofMillis(200))) // debounce 200ms
        .publishOn(scheduler)
        // 1: get current offset from memory or repo
        .flatMap(
            _ignore ->
                getOffsets()
                    // 3: get events from journal with globalSequence > last sequence
                    .flatMap(offsets -> getEventsSince(offsets))
                    // 4: handle events
                    .flatMap(
                        events -> rxtx.execute(txStatus -> handleAndSave(events)).singleOrEmpty()),
            1);
  }

  private Mono<Void> handleAndSave(List<EventHolder<E>> events) {
    return applyEvents(events)
        // 5: save offset in repo
        .flatMap(
            offsets ->
                saveOffsetsInRepo(offsets)
                    // 6: save offset in memory
                    .then(saveOffsetsInMemory(offsets)));
  }

  public void triggerProjection() {
    this.trigger.tryEmitNext(0L);
  }

  // Get the global offset for all shards of this projection
  public Mono<Map<Long, Optional<Long>>> getOffsets() {
    return getOffsetsFromMemory()
        .flatMap(maybeMemoryOffsets -> getOffsetsFromRepo(maybeMemoryOffsets));
  }

  private Mono<Map<Long, Optional<Long>>> getOffsetsFromMemory() {
    return Mono.fromCallable(
        () ->
            shards.stream()
                .map(
                    shard ->
                        Pair.of(
                            shard,
                            Optional.ofNullable(
                                cache.get(new ProjectionId(getProjectionName(), shard)))))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
  }

  private Mono<Void> saveOffsetsInMemory(Map<Long, Long> offsets) {
    return Mono.fromRunnable(
        () -> {
          offsets
              .entrySet()
              .forEach(
                  e -> {
                    ProjectionId projectionId = new ProjectionId(getProjectionName(), e.getKey());
                    cache.put(projectionId, e.getValue());
                  });
          LOG.info("saveOffsetsInMemory continue = " + !offsets.isEmpty());
          if (!offsets.isEmpty()) {
            triggerProjection();
          }
        });
  }

  private Mono<Map<Long, Optional<Long>>> getOffsetsFromRepo(
      Map<Long, Optional<Long>> maybeMemoryOffsets) {
    LOG.info("getOffsetsFromRepo");
    Set<Long> shardsToGet =
        maybeMemoryOffsets.entrySet().stream()
            .filter(e -> !e.getValue().isPresent())
            .map(e -> e.getKey())
            .collect(Collectors.toSet());
    return projectionOffsetRepository
        .getOffsets(getProjectionName(), shardsToGet)
        .collectList()
        .map(
            r -> {
              r.forEach(e -> maybeMemoryOffsets.put(e.shard(), Optional.ofNullable(e.sequence())));
              LOG.info("getOffsetsFromRepo = " + maybeMemoryOffsets.size());
              return maybeMemoryOffsets;
            });
  }

  private Mono<Void> upsertOffsetWithCheck(String projection, long shard, long sequence) {
    return projectionOffsetRepository
        .upsertOffset(projection, shard, sequence)
        .handle(
            (r, sink) -> {
              if (r == 0)
                sink.error(
                    new OptimisticLockingFailureException(
                        "Trying to update offset for projection "
                            + projection
                            + ", shard "
                            + shard
                            + " with sequence ( "
                            + sequence
                            + ") smaller than current one"));
              else sink.next(r);
            })
        .then();
  }

  private Mono<Void> saveOffsetsInRepo(Map<Long, Long> offsets) {
    return Flux.mergeSequential(
            offsets.entrySet().stream()
                .map(e -> upsertOffsetWithCheck(getProjectionName(), e.getKey(), e.getValue()))
                .toList())
        .collectList()
        .then();
  }

  private Mono<List<EventHolder<E>>> getEventsSince(Map<Long, Optional<Long>> offsets) {
    return Flux.mergeSequential(
            offsets.entrySet().stream()
                .map(
                    e -> {
                      long shard = e.getKey();
                      long since = e.getValue().orElse(0L);
                      // LOG.info("getEventsSince on " + shard + " since " + since);
                      return journalRepository
                          .getEventsOnShardSince(shard, since, getMaxEvents(), eventType)
                          .collectList();
                    })
                .toList())
        .collectList()
        .map(list -> list.stream().flatMap(Collection::stream).collect(Collectors.toList()));
  }

  private Mono<Map<Long, Long>> applyEvents(List<EventHolder<E>> events) {
    Map<Long, Long> offsets =
        events.stream()
            .collect(
                Collectors.groupingBy(
                    EventHolder::shard,
                    Collectors.maxBy(Comparator.comparingLong(EventHolder::globalSequence))))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().get().globalSequence()));
    LOG.info("applyEvents " + events.size() + " offsets = " + offsets.size());
    return handleEvents(events).then(Mono.just(offsets));
  }

  // Can be overloaded to handle multiple events in one time.
  protected abstract Mono<Void> handleEvents(List<EventHolder<E>> events);

  public abstract String getProjectionName();

  protected abstract ID toId(String id);

  protected String fromId(ID id) {
    return id.toString();
  }

  protected int getMaxEvents() {
    return 100;
  }
}
