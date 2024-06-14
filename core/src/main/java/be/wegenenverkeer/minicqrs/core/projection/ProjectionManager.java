package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.wegenenverkeer.minicqrs.core.projection.AbstractProjection.ProjectionId;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProjectionManager {
  private static Logger LOG = LoggerFactory.getLogger(ProjectionManager.class);

  private List<? extends AbstractProjection<?, ?>> projections;

  @Autowired
  private Cache<ProjectionId, Long> cache;

  @Autowired
  private ProjectionOffsetRepository projectionOffsetRepository;

  ProjectionManager(List<? extends AbstractProjection<?, ?>> projections) {
    this.projections = projections;
  }

  @PostConstruct
  public void startup() {
    LOG.info("Starting all projections: "
        + projections.stream().map(p -> p.getProjectionName()).collect(Collectors.joining(", ")));
    Flux.merge(projections.stream().map(p -> p.start()).toList())
        .subscribe();
  }

  public void triggerProjections() {
    LOG.info("Triggering all projections: "
        + projections.stream().map(p -> p.getProjectionName()).collect(Collectors.joining(", ")));
    projections.forEach(projection -> projection.triggerProjection());
  }

  public Mono<Boolean> hasOffset(String projectionName, ProjectionOffset offset) {
    return Optional.ofNullable(cache.get(new ProjectionId(projectionName, offset.shard())))
        .map(o -> Mono.just(o >= offset.offset()))
        .orElse(projectionOffsetRepository.hasOffset(projectionName, offset.shard(), offset.offset()));
  }
}
