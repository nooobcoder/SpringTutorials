package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

    Optional<Recipe> findByDescription(String description);
}
