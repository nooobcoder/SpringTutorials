package guru.springfamework.repositories;

import guru.springfamework.domain.Category;
import guru.springfamework.services.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name) throws ResourceNotFoundException;
}
