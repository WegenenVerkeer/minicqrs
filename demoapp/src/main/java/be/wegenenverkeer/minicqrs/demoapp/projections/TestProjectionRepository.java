package be.wegenenverkeer.minicqrs.demoapp.projections;

import static be.wegenenverkeer.minicqrs.demoapp.db.Public.PUBLIC;

import java.util.Optional;
import java.util.UUID;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class TestProjectionRepository {
  private final DSLContext ctx;

  TestProjectionRepository(DSLContext ctx) {
    this.ctx = ctx;
  }

  public Mono<Optional<Long>> findById(UUID id) {
    return Mono.from(ctx
        .select(PUBLIC.TEST_PROJECTION.COUNTER)
        .from(PUBLIC.TEST_PROJECTION)
        .where(PUBLIC.TEST_PROJECTION.ID.eq(id))
        .limit(1))
        .map(Record1::value1)
        .map(Optional::of).defaultIfEmpty(Optional.empty());
  }

  public Mono<Integer> upsert(UUID id, long counter) {
    return Mono.from(ctx
        .insertInto(PUBLIC.TEST_PROJECTION, PUBLIC.TEST_PROJECTION.ID, PUBLIC.TEST_PROJECTION.COUNTER)
        .values(id, counter)
        .onDuplicateKeyUpdate()
        .set(PUBLIC.TEST_PROJECTION.COUNTER, counter));
  }

}
