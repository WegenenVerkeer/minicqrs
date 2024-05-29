package be.wegenenverkeer.minicqrs.parent.projections;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import be.wegenenverkeer.minicqrs.core.projection.AbstractOneByOneProjection;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionId;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionOffsetRepository;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateBehaviour;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.CounterIncremented;
import reactor.core.publisher.Mono;

//@Service
public class TestOneByOneProjection extends AbstractOneByOneProjection<BaseEvent> {
  private static Logger LOG = LoggerFactory.getLogger(TestOneByOneProjection.class);

  private TestProjectionRepository testProjectionRepository;

  public TestOneByOneProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
      ProjectionOffsetRepository projectionOffsetRepository,
      ReactiveTransactionManager tm,
      JournalRepository<BaseEvent> journalRepository, TestProjectionRepository testProjectionRepository) {
    super(objectMapper, cache,
        LongStream.range(0L, TestAggregateBehaviour.NUMBER_OF_SHARDS).boxed().collect(Collectors.toSet()),
        TransactionalOperator.create(tm),
        projectionOffsetRepository, journalRepository, BaseEvent.class);
    this.testProjectionRepository = testProjectionRepository;
  }

  @Override
  public String getProjectionName() {
    return "TestProjectionOneByOne";
  }

  private Mono<Void> increment(UUID id, CounterIncremented event) {
    LOG.info("increment on " + id + " -> " + event);
    return testProjectionRepository.findById(id).map(e -> e.counter()).defaultIfEmpty(0)
        .flatMap(c -> {
          LOG.info("Got counter " + c + " for " + id);
          return testProjectionRepository.upsert(id, c + 1).then();
        });
  }

  @Override
  protected Mono<Void> handleEvent(String id, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> increment(UUID.fromString(id), e);
      default -> throw new UnsupportedOperationException("Unimplemented event " + event);
    };
  }

}
