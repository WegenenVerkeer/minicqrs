package be.wegenenverkeer.minicqrs.core.projection;

import org.springframework.data.relational.core.mapping.Table;

@Table("projection_offset")
public class ProjectionOffsetEntity {  
  private String projection;
  private long shard;
  private Long sequence;

  public ProjectionOffsetEntity() {
  }

  public ProjectionOffsetEntity(String projection, long shard, Long sequence) {
    this.projection = projection;
    this.shard = shard;
    this.sequence = sequence;
  }

  public String getProjection() {
    return projection;
  }

  public void setProjection(String projectionName) {
    this.projection = projectionName;
  }

  public Long getSequence() {
    return sequence;
  }

  public void setSequence(Long sequence) {
    this.sequence = sequence;
  }

  public long getShard() {
    return shard;
  }

  public void setShard(long shard) {
    this.shard = shard;
  }

}
