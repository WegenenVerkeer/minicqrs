package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.ehcache.Cache;

import be.wegenenverkeer.minicqrs.core.JournalRepository.EventHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** 
 * Projection where events are grouped by id, and then handled all together in one transaction.
 */
public abstract class AbstractGroupedByIdProjection<ID,E> extends AbstractProjection<ID,E> {
  public AbstractGroupedByIdProjection(Cache<ProjectionId, Long> cache, Set<Long> shards, Class<E> eventClass) {
    super(cache, shards, eventClass);
  }

  @Override
  protected Mono<Void> handleEvents(List<EventHolder<E>> events) {
    Map<String, List<EventHolder<E>>> grouped = events.stream().collect(Collectors.groupingBy(e -> e.id()));
    return Flux
        .concat(grouped.entrySet().stream()
            .map(e -> handleEventsById(toId(e.getKey()), e.getValue().stream().map(r -> r.event()).toList())).toList())
        .collectList().then();
  }

  protected abstract Mono<Void> handleEventsById(ID id, List<E> events);
}
