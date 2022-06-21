package eth.nooobcoder.jugtours.bootstrap;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import eth.nooobcoder.jugtours.model.Event;
import eth.nooobcoder.jugtours.model.Group;
import eth.nooobcoder.jugtours.repositories.GroupRepository;

@Component
public class InitializerBootstrap implements CommandLineRunner {

  private final GroupRepository repository;

  public InitializerBootstrap(GroupRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG",
        "London JUG").forEach(name -> repository.save(new Group(name)));

    Group djug = repository.findByName("Seattle JUG");
    Event e = Event.builder().title("Micro Frontends for Java Developers")
        .description("JHipster now has microfrontend support!")
        .date(Instant.parse("2022-09-13T17:00:00.000Z"))
        .build();
    djug.setEvents(Collections.singleton(e));
    repository.save(djug);
  }
}
