package be.wegenenverkeer.minicqrs.parent.aggregate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class TestAggregateDomain {
  // ------ Commands
  public static interface BaseCommand {
  };

  public static class IncrementCounter implements BaseCommand {
  }

  // ------ State
  public static record State(int counter) {
    public State increment() {
      return new State(counter + 1);
    }
  };

  // ------ Events
  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
  @JsonSubTypes({
      @JsonSubTypes.Type(value = CounterIncremented.class, name = "CounterIncremented") 
    }
  )
  public static interface BaseEvent {
  };

  public static record CounterIncremented(int previousValue, int currentValue) implements BaseEvent {
  }

}
