package be.wegenenverkeer.minicqrs.demoapp.aggregate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.ehcache.CacheManager;
import org.springframework.stereotype.Service;

import be.wegenenverkeer.minicqrs.core.AbstractAggregateBehaviour;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.BaseCommand;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.CounterIncremented;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.IncrementCounter;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.State;

@Service
public class TestAggregateBehaviour extends
    AbstractAggregateBehaviour<UUID, State, BaseCommand, BaseEvent> {
  public static int NUMBER_OF_SHARDS = 10;

  public TestAggregateBehaviour(CacheManager cacheManager) {
    super(cacheManager, BaseEvent.class, State.class);
  }

  @Override
  protected State applyEvent(UUID id, State state, BaseEvent event) {
    return switch (event) {
      case CounterIncremented e -> state.increment();
    };
  }

  @Override
  protected List<BaseEvent> applyCommand(UUID id, State state, BaseCommand command) {
    return switch (command) {
      case IncrementCounter c -> Arrays.asList(new CounterIncremented(state.counter(), state.counter() + 1));
    };
  }

  @Override
  protected State emptyState() {
    return new State(0);
  }

  @Override
  protected long getShard(UUID id) {
    // 10 shards for this aggregate
    long shard = Math.abs(id.hashCode()) % NUMBER_OF_SHARDS;
    return shard;
  }

  @Override
  protected UUID toId(String id) {
    return UUID.fromString(id);
  }

}
