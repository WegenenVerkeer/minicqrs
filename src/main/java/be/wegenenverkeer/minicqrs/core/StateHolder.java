package be.wegenenverkeer.minicqrs.core;

public record StateHolder<S>(String id, S state, long sequence) {
  public StateHolder<S> advance(S newState) {
    return new StateHolder<>(id, newState, sequence + 1);
  }
}
