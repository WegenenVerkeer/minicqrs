package be.wegenenverkeer.minicqrs.core.projection;

import java.time.Duration;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.function.ThrowingFunction;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import be.wegenenverkeer.minicqrs.core.EventHolder;
import be.wegenenverkeer.minicqrs.core.journal.JournalEntity;
import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public abstract class AbstractProjection<ID,E> {
  private static Logger LOG = LoggerFactory.getLogger(AbstractProjection.class);

  private ProjectionOffsetRepository projectionOffsetRepository;
  private JournalRepository<E> journalRepository;

  private Cache<ProjectionId, Long> cache;
  private Set<Long> shards;
  private JavaType eventType;
  private ObjectMapper objectMapper;
  private Sinks.Many<Long> trigger;
  private TransactionalOperator rxtx;

  public AbstractProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
      Set<Long> shards, TransactionalOperator rxtx,
      ProjectionOffsetRepository projectionOffsetRepository,
      JournalRepository<E> journalRepository, Class<E> eventClass) {
    this.projectionOffsetRepository = projectionOffsetRepository;
    this.journalRepository = journalRepository;
    this.rxtx = rxtx;
    this.objectMapper = objectMapper;
    this.cache = cache;
    this.shards = shards;
    this.eventType = TypeFactory.defaultInstance().constructType(eventClass);
    this.trigger = Sinks.many().replay().latest();
  }

  public Flux<Void> start() {
    triggerProjection();
    Scheduler scheduler = Schedulers.newParallel(getProjectionName(), 1);
    return trigger.asFlux()
        .sampleTimeout(u -> Mono.empty().delaySubscription(Duration.ofMillis(200))) // debounce 200ms
        .publishOn(scheduler)
        // 1: get current offset from memory
        .flatMap(_ignore -> getOffsetsFromMemory()
            .flatMap(maybeMemoryOffsets -> getOffsetsFromRepo(maybeMemoryOffsets))
            // 3: get events from journal with globalSequence > last sequence
            .flatMap(offsets -> getEventsSince(offsets))
            // 4: handle events
            .flatMap(events -> rxtx.execute(txStatus -> handleAndSave(events)).singleOrEmpty()), 1);
  }

  private Mono<Void> handleAndSave(List<JournalEntity<E>> events) {
    return applyEvents(events)
        // 5: save offset in repo
        .flatMap(offsets -> saveOffsetsInRepo(offsets)
            // 6: save offset in memory
            .then(saveOffsetsInMemory(offsets)));
  }

  public void triggerProjection() {
    this.trigger.tryEmitNext(0L);
  }

  private Mono<Map<Long, Optional<Long>>> getOffsetsFromMemory() {
    return Mono.fromCallable(() -> shards.stream()
        .map(shard -> new ProjectionOffsetEntity(getProjectionName(), shard,
            cache.get(new ProjectionId(getProjectionName(), shard))))
        .collect(Collectors.toMap(ProjectionOffsetEntity::shard,
            e -> Optional.ofNullable(e.sequence()))));
  }

  private Mono<Void> saveOffsetsInMemory(Map<Long, Long> offsets) {
    return Mono.fromRunnable(() -> {
      offsets.entrySet().forEach(e -> {
        ProjectionId projectionId = new ProjectionId(getProjectionName(), e.getKey());
        cache.put(projectionId, e.getValue());
      });
      LOG.info("saveOffsetsInMemory continue = " + !offsets.isEmpty());
      if (!offsets.isEmpty()) {
        triggerProjection();
      }
    });
  }

  private Mono<Map<Long, Optional<Long>>> getOffsetsFromRepo(Map<Long, Optional<Long>> maybeMemoryOffsets) {
    LOG.info("getOffsetsFromRepo");
    Set<Long> shardsToGet = maybeMemoryOffsets.entrySet().stream().filter(e -> !e.getValue().isPresent())
        .map(e -> e.getKey()).collect(Collectors.toSet());
    return projectionOffsetRepository.findByProjectionAndShardIn(getProjectionName(), shardsToGet)
        .collectList()
        .map(r -> {
          r.forEach(e -> maybeMemoryOffsets.put(e.shard(), Optional.ofNullable(e.sequence())));
          LOG.info("getOffsetsFromRepo = " + maybeMemoryOffsets.size());
          return maybeMemoryOffsets;
        });
  }

  private Mono<Void> saveOffsetsInRepo(Map<Long, Long> offsets) {
    return Flux
        .mergeSequential(offsets.entrySet().stream()
            .map(e -> projectionOffsetRepository.upsertOffsetWithCheck(getProjectionName(), e.getKey(), e.getValue()))
            .toList())
        .collectList().then();
  }

  private Mono<List<JournalEntity<E>>> getEventsSince(Map<Long, Optional<Long>> offsets) {
    return Flux.mergeSequential(offsets.entrySet().stream().map(e -> {
      long shard = e.getKey();
      long since = e.getValue().orElse(0L);
      // LOG.info("getEventsSince on " + shard + " since " + since);
      return journalRepository
          .findForProjection(shard, eventType.toCanonical(), since, 100) // TODO: configurable
          .collectList();
    }).toList()).collectList().map(list -> list.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList()));
  }

  private Mono<Map<Long, Long>> applyEvents(List<JournalEntity<E>> events) {
    List<EventHolder<E>> eventsToHandle = events.stream()
        .map(ThrowingFunction.of(entity -> entity.toHolder(objectMapper, eventType))).toList();
    Map<Long, Long> offsets = events.stream()
        .collect(Collectors.groupingBy(JournalEntity::shard,
            Collectors.maxBy(Comparator.comparingLong(JournalEntity::globalSequence))))
        .entrySet().stream()
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().get().globalSequence()));
    LOG.info("applyEvents " + events.size() + " offsets = " + offsets.size());
    return handleEvents(eventsToHandle).then(Mono.just(offsets));
  }

  // Can be overloaded to handle multiple events in one time.
  protected abstract Mono<Void> handleEvents(List<EventHolder<E>> events);

  public abstract String getProjectionName();
  protected abstract ID toId(String id);

  protected String fromId(ID id) {
    return id.toString();
  }
}
