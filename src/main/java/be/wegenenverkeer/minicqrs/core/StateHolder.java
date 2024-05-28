package be.wegenenverkeer.minicqrs.core;

public record StateHolder<ID, S>(ID id, S state, long sequence) {
  public StateHolder<ID,S> advance(S newState) {
    return new StateHolder<>(id, newState, sequence + 1);
  }
}
