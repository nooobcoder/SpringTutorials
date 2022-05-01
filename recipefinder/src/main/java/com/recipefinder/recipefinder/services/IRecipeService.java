package com.recipefinder.recipefinder.services;

import com.recipefinder.recipefinder.commands.RecipeCommand;
import com.recipefinder.recipefinder.model.Recipe;

import java.util.Set;

public interface IRecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}