package be.wegenenverkeer.minicqrs.parent.projections;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("test_projection")
class TestProjectionEntity {
  @Id
  private UUID id;
  private int counter;

  public TestProjectionEntity() {
  }

  public TestProjectionEntity(UUID id, int counter) {
    this.id = id;
    this.counter = counter;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getCounter() {
    return counter;
  }

  public void setCounter(int counter) {
    this.counter = counter;
  }
}
