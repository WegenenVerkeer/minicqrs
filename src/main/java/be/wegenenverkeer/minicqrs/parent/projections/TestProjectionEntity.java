package be.wegenenverkeer.minicqrs.parent.projections;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("test_projection")
public record TestProjectionEntity(
    @Id UUID id,
    int counter) {
}
