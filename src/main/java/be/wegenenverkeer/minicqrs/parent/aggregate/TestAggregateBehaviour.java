package be.wegenenverkeer.minicqrs.parent.aggregate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.ehcache.CacheManager;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import be.wegenenverkeer.minicqrs.core.AbstractAggregateBehaviour;
import be.wegenenverkeer.minicqrs.core.journal.JournalRepository;
import be.wegenenverkeer.minicqrs.core.projection.ProjectionManager;
import be.wegenenverkeer.minicqrs.core.snapshot.SnapshotRepository;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.BaseCommand;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.CounterIncremented;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.IncrementCounter;
import be.wegenenverkeer.minicqrs.parent.aggregate.TestAggregateDomain.State;

@Service
public class TestAggregateBehaviour extends
    AbstractAggregateBehaviour<State, BaseCommand, BaseEvent> {
  public static int NUMBER_OF_SHARDS = 10;

  public TestAggregateBehaviour(ObjectMapper objectMapper,
      ProjectionManager projectionManager,
      JournalRepository<BaseEvent> journalRepository,
      SnapshotRepository<State> snapshotRepository,
      CacheManager cacheManager) {
    super(
        objectMapper,
        cacheManager,
        projectionManager,
        journalRepository,
        snapshotRepository,
        BaseEvent.class,
        State.class);
  }

  @Override
  protected State applyEvent(State state, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> state.increment();
      default -> throw new UnsupportedOperationException("Unimplemented event " + event);
    };
  }

  @Override
  protected List<BaseEvent> applyCommand(State state, BaseCommand command) {
    return switch (command) {
      case IncrementCounter c -> Arrays.asList(new CounterIncremented(state.counter(), state.counter() + 1));
      default -> throw new UnsupportedOperationException("Unimplemented command " + command);
    };
  }

  @Override
  protected State emptyState() {
    return new State(0);
  }

  @Override
  protected long getShard(String id) {
    // 10 shards for this aggregate
    long shard = Math.abs(id.hashCode()) % NUMBER_OF_SHARDS;
    return shard;
  }

}
