package com.recipefinder.services;

import com.recipefinder.commands.RecipeCommand;
import com.recipefinder.model.Recipe;

import java.util.Set;

public interface IRecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);

    void deleteById(Long idToDelete);
}