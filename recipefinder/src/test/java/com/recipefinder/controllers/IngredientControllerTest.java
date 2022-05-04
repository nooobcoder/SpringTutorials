package com.recipefinder.controllers;

import com.recipefinder.commands.IngredientCommand;
import com.recipefinder.commands.RecipeCommand;
import com.recipefinder.services.IngredientService;
import com.recipefinder.services.RecipeService;
import com.recipefinder.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;
    IngredientController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new IngredientController(ingredientService, recipeService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        // Given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        // When
        mockMvc.perform(get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/list"))
            .andExpect(model().attributeExists("recipe"));

        // Then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
        // Given
        IngredientCommand ingredientCommand = new IngredientCommand();

        // When
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        // Then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/show"))
            .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testNewIngredientForm() throws Exception {
        // Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        // When
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        // Then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientForm"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));

        verify(recipeService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        // Given
        IngredientCommand ingredientCommand = new IngredientCommand();

        // When
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        // Then
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/ingredient/ingredientForm"))
            .andExpect(model().attributeExists("ingredient"))
            .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        // Given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        // When
        when(ingredientService.saveIngredientCommand(any())).thenReturn(command);

        // Then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }

    @Test
    public void testDeleteIngredient() throws Exception {

        // Then
        mockMvc.perform(delete("/recipe/2/ingredient/3/delete")
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyLong(), anyLong());
    }
}