package com.recipefinder.recipefinder.controllers;

import com.recipefinder.recipefinder.model.Recipe;
import com.recipefinder.recipefinder.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeController controller;
    @Mock
    RecipeService service;

    @BeforeEach
    void setUp() {
        controller = new RecipeController(service);
    }

    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe = new Recipe();
        final long RECIPE_ID = 1L;
        recipe.setId(RECIPE_ID);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(service.findById(RECIPE_ID)).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/show"));
    }
}