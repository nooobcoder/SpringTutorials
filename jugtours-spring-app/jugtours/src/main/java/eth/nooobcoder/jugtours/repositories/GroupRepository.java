package eth.nooobcoder.jugtours.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eth.nooobcoder.jugtours.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
  Group findByName(String name);
}
