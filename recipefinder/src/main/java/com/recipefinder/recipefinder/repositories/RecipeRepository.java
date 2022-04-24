package com.recipefinder.recipefinder.repositories;

import com.recipefinder.recipefinder.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
