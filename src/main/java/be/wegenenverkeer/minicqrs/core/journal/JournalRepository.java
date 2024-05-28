package be.wegenenverkeer.minicqrs.core.journal;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface JournalRepository<ID, E> extends R2dbcRepository<JournalEntity<ID, E>, ID> {
  Flux<JournalEntity<ID, E>> findByIdAndTypeAndSequenceGreaterThanOrderBySequence(ID id, String type, long sequence);
  Flux<JournalEntity<ID, E>> findByShardAndTypeAndGlobalSequenceGreaterThanOrderByGlobalSequence(long shard, String type, long globalSequence, Pageable pageable);

  default Flux<JournalEntity<ID, E>> findForProjection(long shard, String type, long globalSequence, int maxEvents) {
    return findByShardAndTypeAndGlobalSequenceGreaterThanOrderByGlobalSequence(shard, type, globalSequence, PageRequest.of(0,maxEvents));
  }

}
