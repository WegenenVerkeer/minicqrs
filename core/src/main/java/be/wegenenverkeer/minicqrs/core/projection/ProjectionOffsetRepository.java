package be.wegenenverkeer.minicqrs.core.projection;

import static be.wegenenverkeer.minicqrs.core.db.Tables.PROJECTION_OFFSET;
import static org.jooq.impl.DSL.and;
import static org.jooq.impl.DSL.exists;

import be.wegenenverkeer.minicqrs.core.db.tables.records.ProjectionOffsetRecord;
import java.util.Set;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProjectionOffsetRepository {
  public static record Offset(long shard, long sequence) {}

  private final DSLContext ctx;

  public ProjectionOffsetRepository(DSLContext ctx) {
    this.ctx = ctx;
  }

  public Flux<Offset> getOffsets(String projectionName, Set<Long> shards) {
    return Flux.from(
            ctx.selectFrom(PROJECTION_OFFSET)
                .where(
                    and(
                        PROJECTION_OFFSET.PROJECTION.eq(projectionName),
                        PROJECTION_OFFSET.SHARD.in(shards))))
        .map(r -> new Offset(r.getShard(), r.getSequence()));
  }

  public Mono<Boolean> hasOffset(String projectionName, Long shard, Long sequence) {
    return Mono.from(
            ctx.select(
                exists(
                    ctx.selectFrom(PROJECTION_OFFSET)
                        .where(
                            and(
                                PROJECTION_OFFSET.PROJECTION.eq(projectionName),
                                PROJECTION_OFFSET.SHARD.eq(shard),
                                PROJECTION_OFFSET.SEQUENCE.ge(sequence))))))
        .map(r -> r.value1());
  }

  public Mono<Integer> upsertOffset(String projection, long shard, long sequence) {
    ProjectionOffsetRecord record = new ProjectionOffsetRecord(projection, shard, sequence);

    return Mono.from(
        ctx.insertInto(PROJECTION_OFFSET)
            .columns(record.fields())
            .valuesOfRecords(record)
            .onDuplicateKeyUpdate()
            .set(record)
            .where(PROJECTION_OFFSET.SEQUENCE.lt(sequence)));
  }
}
