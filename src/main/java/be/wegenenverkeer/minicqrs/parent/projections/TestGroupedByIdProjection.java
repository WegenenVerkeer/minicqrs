package be.wegenenverkeer.minicqrs.parent.projections;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.ehcache.Cache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import be.wegenenverkeer.minicqrs.core.projection.AbstractGroupedByIdWithStateProjection;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionId;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionOffsetRepository;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateBehaviour;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.CounterIncremented;
import reactor.core.publisher.Mono;

@Service
public class TestGroupedByIdProjection extends AbstractGroupedByIdWithStateProjection<UUID, BaseEvent, Integer> {
  private TestProjectionRepository testProjectionRepository;

  public TestGroupedByIdProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
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
    return "TestProjectionGroupedById";
  }

  @Override
  protected Integer getEmptyState(UUID id) {
    return 0;
  }

  @Override
  protected Mono<Integer> getState(UUID id) {
    return testProjectionRepository.findById(id).map(e -> e.counter());
  }

  @Override
  protected Mono<Void> saveState(UUID id, Integer state) {
    return testProjectionRepository.upsert(id, state).then();
  }

  @Override
  protected Integer handleEvent(Integer state, UUID id, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> state + 1;
    };
  }

  @Override
  protected UUID toId(String id) {
    return UUID.fromString(id);
  }

}
