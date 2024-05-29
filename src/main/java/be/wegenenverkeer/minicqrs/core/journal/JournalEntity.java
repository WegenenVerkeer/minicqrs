package be.wegenenverkeer.minicqrs.core.journal;

import java.time.Instant;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.EventHolder;

@Table("journal")
public record JournalEntity<E>(
    String id,
    JsonNode event,
    long sequence,
    String type,
    Instant occured,
    long shard,
    Long globalSequence) {

  public JournalEntity(ObjectMapper objectMapper, EventHolder<E> eventHolder, String type, long shard) {
    this(eventHolder.id(), objectMapper.valueToTree(eventHolder.event()), eventHolder.sequence(), type,
        eventHolder.occured(), shard, null);
  }

  public EventHolder<E> toHolder(ObjectMapper objectMapper, JavaType eventType)
      throws JsonProcessingException, IllegalArgumentException {
    return new EventHolder<E>(id, objectMapper.treeToValue(event, eventType), sequence, occured);
  }
}
