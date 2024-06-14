package be.wegenenverkeer.minicqrs.core.projection;

public record ProjectionOffset(long shard, long offset) {
  @Override
  public final String toString() {
    return shard + "|" + offset;
  }

  public static ProjectionOffset fromString(String str) {
    String[] parts = str.split("\\|");
    if (parts.length == 2) {
      return new ProjectionOffset(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }
    throw new IllegalArgumentException("Cannot convert \"" + str + "\" to ProjectionOffset");
  }
}
