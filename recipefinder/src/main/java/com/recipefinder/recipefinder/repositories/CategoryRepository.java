package com.recipefinder.recipefinder.repositories;

import com.recipefinder.recipefinder.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
