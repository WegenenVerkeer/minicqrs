package be.wegenenverkeer.minicqrs.core.projection;

import java.util.Set;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectionOffsetRepository extends R2dbcRepository<ProjectionOffsetEntity, String> {
  Flux<ProjectionOffsetEntity> findByProjectionAndShardIn(String projection, Set<Long> shard);

  // Make sure that when we update the sequence, it is monotone rising. This to
  // avoid duplicate handling.
  @Modifying
  @Query("INSERT INTO projection_offset as old (projection, shard, \"sequence\") VALUES (:projection, :shard, :sequence) ON CONFLICT(projection, shard) DO UPDATE SET \"sequence\" = EXCLUDED.\"sequence\" WHERE EXCLUDED.\"sequence\" > old.\"sequence\"")
  Mono<Integer> upsertOffset(String projection, long shard, long sequence);

  default Mono<Void> upsertOffsetWithCheck(String projection, long shard, long sequence) {
    return upsertOffset(projection, shard, sequence)
        .handle((r, sink) -> {
          if (r == 0)
            sink.error(new OptimisticLockingFailureException("Trying to update offset for projection " + projection
                + ", shard " + shard + " with sequence ( " + sequence + ") smaller than current one"));
          else
            sink.next(r);
        }).then();
  }

}
