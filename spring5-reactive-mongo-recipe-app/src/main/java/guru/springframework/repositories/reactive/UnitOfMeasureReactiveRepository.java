package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
