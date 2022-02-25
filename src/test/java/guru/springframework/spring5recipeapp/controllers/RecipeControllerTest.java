package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RecipeControllerTest {

  @Mock
  RecipeService recipeService;

  RecipeController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    controller = new RecipeController(recipeService);
  }

  @Test
  public void testGetRecipe() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    Mockito.when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);

    mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("recipe/show"));
  }

}
