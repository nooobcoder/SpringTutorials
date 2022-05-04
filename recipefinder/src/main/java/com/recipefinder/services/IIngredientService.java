package com.recipefinder.services;

import com.recipefinder.commands.IngredientCommand;
import org.springframework.transaction.annotation.Transactional;

public interface IIngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    @Transactional
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);
}
