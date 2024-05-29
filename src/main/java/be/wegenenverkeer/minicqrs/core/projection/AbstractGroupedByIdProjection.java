package be.wegenenverkeer.minicqrs.core.projection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.ehcache.Cache;
import org.springframework.transaction.reactive.TransactionalOperator;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.wegenenverkeer.minicqrs.core.EventHolder;
import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractGroupedByIdProjection<ID,E> extends AbstractProjection<ID,E> {
  public AbstractGroupedByIdProjection(ObjectMapper objectMapper, Cache<ProjectionId, Long> cache,
      Set<Long> shards, TransactionalOperator rxtx,
      ProjectionOffsetRepository projectionOffsetRepository,
      JournalRepository<E> journalRepository, Class<E> eventClass) {
    super(objectMapper, cache, shards, rxtx, projectionOffsetRepository, journalRepository, eventClass);
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
