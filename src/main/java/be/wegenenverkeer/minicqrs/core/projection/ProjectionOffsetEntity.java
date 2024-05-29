package be.wegenenverkeer.minicqrs.core.projection;

import org.springframework.data.relational.core.mapping.Table;

@Table("projection_offset")
public record ProjectionOffsetEntity(
    String projection,
    long shard,
    Long sequence) {
}
