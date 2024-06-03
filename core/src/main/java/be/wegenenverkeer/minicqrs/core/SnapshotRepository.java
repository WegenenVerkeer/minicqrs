package be.wegenenverkeer.minicqrs.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import be.wegenenverkeer.minicqrs.core.db.tables.records.SnapshotRecord;
import reactor.core.publisher.Mono;

import static be.wegenenverkeer.minicqrs.core.db.Tables.SNAPSHOT;
import static org.jooq.JSONB.jsonb;
import static org.jooq.impl.DSL.and;

import java.util.Optional;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SnapshotRepository<S> {
  private static Logger LOG = LoggerFactory.getLogger(SnapshotRepository.class);

  public static record StateHolder<S>(String id, S state, long sequence, JavaType type) {
    public StateHolder<S> advance(S newState) {
      return new StateHolder<>(id, newState, sequence + 1, type);
    }
  }

  private final ObjectMapper objectMapper;
  private final DSLContext ctx;

  public SnapshotRepository(DSLContext ctx, ObjectMapper objectMapper) {
    this.ctx = ctx;
    this.objectMapper = objectMapper;
  }

  private SnapshotRecord toDb(StateHolder<S> stateHolder) {
    try {
      return new SnapshotRecord(stateHolder.id(), jsonb(objectMapper.writeValueAsString(stateHolder.state())),
          stateHolder.sequence(), stateHolder.type().toCanonical());
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Could not convert StateHolder to SnapshotRecord", e);
    }
  }

  private StateHolder<S> fromDb(SnapshotRecord record) {
    try {
      JavaType type = TypeFactory.defaultInstance().constructFromCanonical(record.getType());
      return new StateHolder<S>(record.getId(), objectMapper.readValue(record.getSnapshot().data(), type),
          record.getSequence(), type);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Could not convert SnapshotRecord to StateHolder", e);
    }
  }

  public Mono<Optional<StateHolder<S>>> getLatestSnapshot(String id, JavaType stateType) {
    LOG.info("getLatestSnapshot");
    return Mono.from(ctx
        .selectFrom(SNAPSHOT)
        .where(and(
            SNAPSHOT.ID.eq(id),
            SNAPSHOT.TYPE.eq(stateType
                .toCanonical())))
        .limit(1))
        .map(record -> fromDb(record))
        .map(Optional::of).defaultIfEmpty(Optional.empty());
  }

  public Mono<Integer> saveSnapshot(StateHolder<S> state) {
    var record = toDb(state);
    return Mono.from(ctx.insertInto(SNAPSHOT)
        .columns(SNAPSHOT.fields())
        .valuesOfRecords(record)
        .onDuplicateKeyUpdate()
        .set(record));
  }

}
