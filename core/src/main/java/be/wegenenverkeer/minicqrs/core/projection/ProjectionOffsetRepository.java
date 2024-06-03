package be.wegenenverkeer.minicqrs.core.projection;

import be.wegenenverkeer.minicqrs.core.db.tables.records.ProjectionOffsetRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static be.wegenenverkeer.minicqrs.core.db.Tables.PROJECTION_OFFSET;
import static org.jooq.impl.DSL.and;

import java.util.Set;

import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class ProjectionOffsetRepository {
  public static record Offset(long shard, long sequence) {
  }

  private final DSLContext ctx;

  public ProjectionOffsetRepository(DSLContext ctx) {
    this.ctx = ctx;
  }

  public Flux<Offset> getOffsets(String projectionName, Set<Long> shards) {
    return Flux.from(ctx
        .selectFrom(PROJECTION_OFFSET)
        .where(and(
            PROJECTION_OFFSET.PROJECTION.eq(projectionName),
            PROJECTION_OFFSET.SHARD.in(shards))))
        .map(r -> new Offset(r.getShard(), r.getSequence()));

  }
  
  public Mono<Integer> upsertOffset(String projection, long shard, long sequence) {
    ProjectionOffsetRecord record = new ProjectionOffsetRecord(projection, shard, sequence);

    return Mono.from(ctx
        .insertInto(PROJECTION_OFFSET)
        .columns(record.fields())
        .valuesOfRecords(record)
        .onDuplicateKeyUpdate()
        .set(record)
        .where(PROJECTION_OFFSET.SEQUENCE.lt(sequence)));
  }

}