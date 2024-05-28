package be.wegenenverkeer.minicqrs.core.snapshot;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.StateHolder;

@Table("snapshot")
public class SnapshotEntity<ID, S> {
    private ID id;
    private JsonNode snapshot;
    private long sequence;
    private String type;

    public SnapshotEntity() {
    }

    public SnapshotEntity(ID id, JsonNode event, long sequence, String type) {
      this.id = id;
      this.snapshot = event;
      this.sequence = sequence;
      this.type = type;
    }

    public SnapshotEntity(ObjectMapper objectMapper, StateHolder<ID, S> stateHolder, String type) {
      this(stateHolder.id(), objectMapper.valueToTree(stateHolder.state()), stateHolder.sequence(), type);
    }

    public ID getId() {
      return id;
    }

    public void setId(ID id) {
      this.id = id;
    }

    public JsonNode getSnapshot() {
      return snapshot;
    }

    public void setSnapshot(JsonNode event) {
      this.snapshot = event;
    }

    public long getSequence() {
      return sequence;
    }

    public void setSequence(long sequence) {
      this.sequence = sequence;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  
    public StateHolder<ID, S> toHolder( ObjectMapper objectMapper, JavaType eventType) throws JsonProcessingException, IllegalArgumentException {
      return new StateHolder<ID,S>(id, objectMapper.treeToValue(snapshot, eventType), sequence);
    }
}
