package be.wegenenverkeer.minicqrs.demoapp.projections;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.ehcache.Cache;
import org.springframework.stereotype.Service;
import be.wegenenverkeer.minicqrs.core.projection.AbstractGroupedByIdWithStateProjection;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateBehaviour;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.CounterIncremented;
import reactor.core.publisher.Mono;

@Service
public class TestGroupedByIdProjection extends AbstractGroupedByIdWithStateProjection<UUID, BaseEvent, Long> {
  private final TestProjectionRepository testProjectionRepository;

  public TestGroupedByIdProjection(Cache<ProjectionId, Long> cache, TestProjectionRepository testProjectionRepository) {
    super(cache, LongStream.range(0L, TestAggregateBehaviour.NUMBER_OF_SHARDS).boxed().collect(Collectors.toSet()), BaseEvent.class);
    this.testProjectionRepository = testProjectionRepository;
  }

  @Override
  public String getProjectionName() {
    return "TestProjectionGroupedById";
  }

  @Override
  protected Long getEmptyState(UUID id) {
    return 0L;
  }

  @Override
  protected Mono<Long> getState(UUID id) {
    return testProjectionRepository.findById(id).map(e -> e.orElse(0L));
  }

  @Override
  protected Mono<Void> saveState(UUID id, Long state) {
    return testProjectionRepository.upsert(id, state).then();
  }

  @Override
  protected Long handleEvent(Long state, UUID id, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> state + 1;
    };
  }

  @Override
  protected UUID toId(String id) {
    return UUID.fromString(id);
  }

}
