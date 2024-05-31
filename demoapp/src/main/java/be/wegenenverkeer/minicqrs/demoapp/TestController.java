package be.wegenenverkeer.minicqrs.demoapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateBehaviour;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.BaseEvent;
import be.wegenenverkeer.minicqrs.demoapp.aggregate.TestAggregateDomain.IncrementCounter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rest/test")
class TestController {
  private TestAggregateBehaviour aggregate;

  TestController(TestAggregateBehaviour aggregate) {
    this.aggregate = aggregate;
  }

  @GetMapping("/{id}/{count}")
  public Mono<List<BaseEvent>> changeStatus(@PathVariable("id") UUID id, @PathVariable("count") int count) throws InterruptedException {

    List<Mono<List<BaseEvent>>> monos = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      monos.add(aggregate.processCommand(id, new IncrementCounter()));
    }
    return Flux.merge(monos).collectList().map(l -> l.getLast());
  }

}
