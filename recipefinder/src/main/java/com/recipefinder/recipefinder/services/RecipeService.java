package com.recipefinder.recipefinder.services;

import com.recipefinder.recipefinder.model.Recipe;
import com.recipefinder.recipefinder.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeService implements IRecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service!");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOp = recipeRepository.findById(l);

        if (recipeOp.isEmpty()) {
            throw new RuntimeException("Recipe not found");
        }
        return recipeOp.get();
    }
}
