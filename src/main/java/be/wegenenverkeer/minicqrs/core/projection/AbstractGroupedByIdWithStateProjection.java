package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Set;
import org.ehcache.Cache;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import reactor.core.publisher.Mono;

public abstract class AbstractGroupedByIdWithStateProjection<E, S> extends AbstractGroupedByIdProjection<E> {
  public AbstractGroupedByIdWithStateProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
      Set<Long> shards, TransactionalOperator rxtx,
      ProjectionOffsetRepository projectionOffsetRepository,
      JournalRepository<E> journalRepository, Class<E> eventClass) {
    super(objectMapper, cache, shards, rxtx, projectionOffsetRepository, journalRepository, eventClass);
  }

  @Override
  protected Mono<Void> handleEventsById(String id, List<E> events) {
    return getState(id).defaultIfEmpty(getEmptyState(id)).map(s -> {
      for (E e : events) {
        s = handleEvent(s, id, e);
      }
      return s;
    })
    .flatMap(s -> saveState(id, s));
  }

  protected abstract S getEmptyState(String id);

  protected abstract Mono<S> getState(String id);

  protected abstract Mono<Void> saveState(String id, S state);

  protected abstract S handleEvent(S state, String id, E event);
}
