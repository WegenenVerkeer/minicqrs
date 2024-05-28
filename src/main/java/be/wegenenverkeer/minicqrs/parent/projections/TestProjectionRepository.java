package be.wegenenverkeer.minicqrs.parent.projections;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;

interface TestProjectionRepository extends R2dbcRepository<TestProjectionEntity, UUID> {
  @Modifying
  @Query("INSERT INTO test_projection (id, \"counter\") VALUES (:id, :counter) ON CONFLICT(id) DO UPDATE SET \"counter\" = EXCLUDED.\"counter\"")
  Mono<Integer> upsert(UUID id, long counter);
  
}
