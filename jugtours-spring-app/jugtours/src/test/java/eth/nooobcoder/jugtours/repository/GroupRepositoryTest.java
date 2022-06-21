package eth.nooobcoder.jugtours.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import eth.nooobcoder.jugtours.model.Group;
import eth.nooobcoder.jugtours.repositories.GroupRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private GroupRepository groupRepository;

  @Test
  public void testFindByName() {
    Group group = new Group();
    group.setName("test");
    entityManager.persist(group);

    Group found = groupRepository.findByName(group.getName());

    assertEquals(found.getName(), found.getName());
  }
}
