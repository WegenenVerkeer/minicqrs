package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Set;
import org.ehcache.Cache;
import reactor.core.publisher.Mono;

/**
 * Simplest projection where one id corresponds with one state. Events are grouped by id and all
 * handled. Handle event is blocking.
 */
public abstract class AbstractGroupedByIdWithStateProjection<ID, E, S>
    extends AbstractGroupedByIdProjection<ID, E> {
  public AbstractGroupedByIdWithStateProjection(
      Cache<ProjectionId, Long> cache, Set<Long> shards, Class<E> eventClass) {
    super(cache, shards, eventClass);
  }

  @Override
  protected Mono<Void> handleEventsById(ID id, List<E> events) {
    return getState(id)
        .defaultIfEmpty(getEmptyState(id))
        .map(
            s -> {
              for (E e : events) {
                s = handleEvent(s, id, e);
              }
              return s;
            })
        .flatMap(s -> saveState(id, s));
  }

  protected abstract S getEmptyState(ID id);

  protected abstract Mono<S> getState(ID id);

  protected abstract Mono<Void> saveState(ID id, S state);

  protected abstract S handleEvent(S state, ID id, E event);
}
