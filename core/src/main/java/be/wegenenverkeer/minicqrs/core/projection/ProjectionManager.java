package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;

@Service
public class ProjectionManager {
  private static Logger LOG = LoggerFactory.getLogger(ProjectionManager.class);

  private List<? extends AbstractProjection<?,?>> projections;

  ProjectionManager(List<? extends AbstractProjection<?,?>> projections) {
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
}
