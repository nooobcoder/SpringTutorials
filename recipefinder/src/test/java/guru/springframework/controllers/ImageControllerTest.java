package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ImageControllerTest {

    public static final long RECIPE_ID = 1L;
    @Mock
    ImageService imageService;
    @Mock
    RecipeService recipeService;

    ImageController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(new ControllerExceptionHandler())
            .build();
    }

    @Test
    public void getImageForm() throws Exception {
        // Given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);

        // When
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        // Then
        mockMvc.perform(get("/recipe/1/image"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception {
        // Given
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Spring Framework Tutorial".getBytes(StandardCharsets.UTF_8));

        // Then
        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception {
        // Given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);

        String s = "FAKE IMAGE TEXT";
        Byte[] bytesBoxed = new Byte[s.getBytes(StandardCharsets.UTF_8).length];

        int i = 0;

        for (byte primByte : s.getBytes(StandardCharsets.UTF_8))
            bytesBoxed[i++] = primByte;

        command.setImage(bytesBoxed);

        // When
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        MockHttpServletResponse resp = mockMvc.perform(get("/recipe/1/recipeimage"))
            .andExpect(status().isOk())
            .andReturn().getResponse();

        byte[] respBytes = resp.getContentAsByteArray();
        assertEquals(s.getBytes(StandardCharsets.UTF_8).length, respBytes.length);
    }

    @Test
    public void testGetImageNumberFormatException() throws Exception {
        mockMvc.perform(get("/recipe/asfd/recipeimage"))
            .andExpect(status().isBadRequest())
            .andExpect(view().name("400error"));
    }
}