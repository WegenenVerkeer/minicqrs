package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Set;

import org.ehcache.Cache;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.EventHolder;
import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractOneByOneProjection<ID,E> extends AbstractProjection<ID,E> {
  public AbstractOneByOneProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
      Set<Long> shards, TransactionalOperator rxtx,
      ProjectionOffsetRepository projectionOffsetRepository,
      JournalRepository<E> journalRepository, Class<E> eventClass) {
    super(objectMapper, cache, shards, rxtx, projectionOffsetRepository, journalRepository, eventClass);
  }

  protected Mono<Void> handleEvents(List<EventHolder<E>> events) {
    return Flux.concat(events.stream().map(e -> handleEvent(e.id(), e.event())).toList()).collectList().then();
  }

  protected abstract Mono<Void> handleEvent(String id, E event);
}
