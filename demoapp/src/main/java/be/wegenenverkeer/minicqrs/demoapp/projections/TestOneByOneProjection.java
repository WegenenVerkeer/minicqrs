package be.wegenenverkeer.minicqrs.demoapp.projections;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import be.wegenenverkeer.minicqrs.core.projection.AbstractOneByOneProjection;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateBehaviour;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.CounterIncremented;
import reactor.core.publisher.Mono;

//@Service
public class TestOneByOneProjection extends AbstractOneByOneProjection<UUID, BaseEvent> {
  private static Logger LOG = LoggerFactory.getLogger(TestOneByOneProjection.class);

  private final TestProjectionRepository testProjectionRepository;

  public TestOneByOneProjection(Cache<ProjectionId, Long> cache, TestProjectionRepository testProjectionRepository) {
    super(cache,
        LongStream.range(0L, TestAggregateBehaviour.NUMBER_OF_SHARDS).boxed().collect(Collectors.toSet()),
        BaseEvent.class);
    this.testProjectionRepository = testProjectionRepository;
  }

  @Override
  public String getProjectionName() {
    return "TestProjectionOneByOne";
  }

  private Mono<Void> increment(UUID id, CounterIncremented event) {
    LOG.info("increment on " + id + " -> " + event);
    return testProjectionRepository.findById(id)
        .map(o -> o.orElse(0L))
        .flatMap(c -> {
          LOG.info("Got counter " + c + " for " + id);
          return testProjectionRepository.upsert(id, c + 1).then();
        });
  }

  @Override
  protected Mono<Void> handleEvent(String id, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> increment(UUID.fromString(id), e);
    };
  }

  @Override
  protected UUID toId(String id) {
    return UUID.fromString(id);
  }
}
