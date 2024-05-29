package be.wegenenverkeer.minicqrs.core.snapshot;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.StateHolder;

@Table("snapshot")
public record SnapshotEntity<S>(
    String id,
    JsonNode snapshot,
    long sequence,
    String type) {

  public SnapshotEntity(ObjectMapper objectMapper, StateHolder<S> stateHolder, String type) {
    this(stateHolder.id(), objectMapper.valueToTree(stateHolder.state()), stateHolder.sequence(), type);
  }

  public StateHolder<S> toHolder(ObjectMapper objectMapper, JavaType eventType)
      throws JsonProcessingException, IllegalArgumentException {
    return new StateHolder<S>(id, objectMapper.treeToValue(snapshot, eventType), sequence);
  }
}
