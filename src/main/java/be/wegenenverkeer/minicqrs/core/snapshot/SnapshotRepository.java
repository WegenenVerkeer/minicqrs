package be.wegenenverkeer.minicqrs.core.snapshot;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;

@Repository
public interface SnapshotRepository<S> extends R2dbcRepository<SnapshotEntity<S>, String> {
  Mono<SnapshotEntity<S>> findByIdAndType(String id, String type);

  Mono<Integer> deleteByIdAndType(String id, String type);

  @Transactional
  default Mono<Integer> replace(SnapshotEntity<S> entity, String type) {
    return deleteByIdAndType(entity.getId(), type).flatMap(e -> save(entity)).map(e -> 1);
  }
}
