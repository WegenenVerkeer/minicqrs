package be.wegenenverkeer.minicqrs.core.journal;

import java.time.Instant;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.EventHolder;

@Table("journal")
public class JournalEntity<E> {
  private String id;
  private JsonNode event;
  private long sequence;
  private long shard;
  private Long globalSequence;
  private Instant occured;
  private String type;

  public JournalEntity() {
  }

  private JournalEntity(String id, JsonNode event, long sequence, String type, Instant occured, long shard) {
    this.id = id;
    this.event = event;
    this.sequence = sequence;
    this.type = type;
    this.occured = occured;
    this.shard = shard;
  }

  public JournalEntity(ObjectMapper objectMapper, EventHolder<E> eventHolder, String type, long shard) {
    this(eventHolder.id(), objectMapper.valueToTree(eventHolder.event()), eventHolder.sequence(), type,
        eventHolder.occured(), shard);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public JsonNode getEvent() {
    return event;
  }

  public void setEvent(JsonNode event) {
    this.event = event;
  }

  public long getSequence() {
    return sequence;
  }

  public void setSequence(long sequence) {
    this.sequence = sequence;
  }


  public Long getGlobalSequence() {
    return globalSequence;
  }

  public void setGlobalSequence(Long globalSequence) {
    this.globalSequence = globalSequence;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Instant getOccured() {
    return occured;
  }

  public void setOccured(Instant occured) {
    this.occured = occured;
  }

  public long getShard() {
    return shard;
  }

  public void setShard(long shard) {
    this.shard = shard;
  }

  public EventHolder<E> toHolder(ObjectMapper objectMapper, JavaType eventType)
      throws JsonProcessingException, IllegalArgumentException {
    return new EventHolder<E>(id, objectMapper.treeToValue(event, eventType), sequence, occured);
  }
}
