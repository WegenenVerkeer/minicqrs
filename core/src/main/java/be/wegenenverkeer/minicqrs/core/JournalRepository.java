package be.wegenenverkeer.minicqrs.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import be.wegenenverkeer.minicqrs.core.db.tables.records.JournalRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static be.wegenenverkeer.minicqrs.core.db.Tables.JOURNAL;
import static org.jooq.JSONB.jsonb;
import static org.jooq.impl.DSL.and;

import java.time.LocalDateTime;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JournalRepository<E> {
  public static record EventHolder<E>(String id, long shard, E event, long sequence, Long globalSequence, LocalDateTime occured, JavaType type) {

}
  private static Logger LOG = LoggerFactory.getLogger(JournalRepository.class);

  private final ObjectMapper objectMapper;
  private final DSLContext ctx;

  public JournalRepository(DSLContext ctx, ObjectMapper objectMapper) {
    this.ctx = ctx;
    this.objectMapper = objectMapper;
  }

  public static Field<?>[] columns = { JOURNAL.ID, JOURNAL.EVENT, JOURNAL.TYPE,
      JOURNAL.SHARD, JOURNAL.OCCURED, JOURNAL.SEQUENCE };

  // We don't want to save the globalSequence, it will be computed by the database
  private org.jooq.Record toDb(EventHolder<E> eventHolder) {
    try {
      org.jooq.Record record = ctx.newRecord(columns);
      record.set(JOURNAL.ID, eventHolder.id());
      record.set(JOURNAL.EVENT, jsonb(objectMapper.writeValueAsString(eventHolder.event())));
      record.set(JOURNAL.TYPE, eventHolder.type().toCanonical());
      record.set(JOURNAL.SHARD, eventHolder.shard());
      record.set(JOURNAL.OCCURED, eventHolder.occured());
      record.set(JOURNAL.SEQUENCE, eventHolder.sequence());
      return record;
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Could not convert EventHolder to JournalRecord", e);
    }
  }

  private EventHolder<E> fromDb(JournalRecord record) {
    try {
      JavaType type = TypeFactory.defaultInstance().constructFromCanonical(record.getType());
      return new EventHolder<E>(record.getId(), record.getShard(),
          objectMapper.readValue(record.getEvent().data(), type),
          record.getSequence(), record.getGlobalSequence(), record.getOccured(), type);
    } catch (JsonProcessingException | IllegalArgumentException e) {
      throw new RuntimeException("Could not convert JournalRecord to EventHolder", e);
    }
  }

  public Mono<List<EventHolder<E>>> getEventsSince(String id, long since, JavaType eventType) {
    LOG.info("getEventsSince on " + id + " since " + since);
    return Flux.from(ctx
        .selectFrom(JOURNAL)
        .where(and(
            JOURNAL.ID.eq(id),
            JOURNAL.TYPE.eq(eventType.toCanonical()),
            JOURNAL.SEQUENCE.gt(since)))
        .orderBy(JOURNAL.SEQUENCE))
        .map(entity -> fromDb(entity)).collectList();
  }

  public Mono<Integer> saveEvents(List<EventHolder<E>> events) {
    var records = events.stream().map(event -> toDb(event)).toList();
    var inserts = ctx.insertInto(JOURNAL)
        .columns(JournalRepository.columns)
        .valuesOfRecords(records);
    return Flux.from(inserts).collectList().map(r -> r.size());
  }

  public Flux<EventHolder<E>> getEventsOnShardSince(long shard, long since, int maxEvents, JavaType eventType) {
    return Flux.from(ctx
        .selectFrom(JOURNAL)
        .where(and(
            JOURNAL.SHARD.eq(shard),
            JOURNAL.TYPE.eq(eventType.toCanonical()),
            JOURNAL.GLOBAL_SEQUENCE.gt(since)))
        .orderBy(JOURNAL.GLOBAL_SEQUENCE)
        .limit(maxEvents))
        .map(entity -> fromDb(entity));
  }
}
