package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Set;

import org.ehcache.Cache;

import be.wegenenverkeer.minicqrs.core.JournalRepository.EventHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Projection where every event is handled on it's own.
 */
public abstract class AbstractOneByOneProjection<ID,E> extends AbstractProjection<ID,E> {
  public AbstractOneByOneProjection(Cache<ProjectionId, Long> cache,Set<Long> shards, Class<E> eventClass) {
    super(cache, shards, eventClass);
  }

  protected Mono<Void> handleEvents(List<EventHolder<E>> events) {
    return Flux.concat(events.stream().map(e -> handleEvent(e.id(), e.event())).toList()).collectList().then();
  }

  protected abstract Mono<Void> handleEvent(String id, E event);
}
