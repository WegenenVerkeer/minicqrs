package be.wegenenverkeer.minicqrs.core.projection;

public record ProjectionId(String projection, long shard) {

}
