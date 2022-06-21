package eth.nooobcoder.jugtours.bootstrap;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import eth.nooobcoder.jugtours.model.Event;
import eth.nooobcoder.jugtours.model.Group;
import eth.nooobcoder.jugtours.repositories.GroupRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitializerBootstrapTest {
  @Autowired
  private GroupRepository repository;

  @Test
  public void test() {

    Group group = new Group();
    group.setName("JUG");

    Event event = new Event();
    event.setDescription("Spring Boot User Group");

    repository.save(group);

    Stream.of(group, event).forEach(System.out::println);
  }
}
