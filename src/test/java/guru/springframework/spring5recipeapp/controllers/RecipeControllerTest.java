package guru.springframework.spring5recipeapp.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RecipeControllerTest {

  @Mock
  RecipeService recipeService;

  RecipeController controller;

  MockMvc mockMvc;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);

    controller = new RecipeController(recipeService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void testGetRecipe() throws Exception {

    Recipe recipe = new Recipe();
    recipe.setId(1L);

    when(recipeService.findById(anyLong())).thenReturn(recipe);

    mockMvc.perform(get("/recipe/show/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/show"))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void testGetNewRecipeForm() throws Exception {
    RecipeCommand command = new RecipeCommand();

    mockMvc.perform(get("/recipe/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/recipeform"))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void testPostNewRecipeForm() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.saveRecipeCommand(any())).thenReturn(command);

    mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//                .param("description", "some string")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/recipe/show/2"));
  }

  @Test
  void testGetUpdateView() throws Exception {
    RecipeCommand command = new RecipeCommand();
    command.setId(2L);

    when(recipeService.findCommandById(anyLong())).thenReturn(command);

    mockMvc.perform(get("/recipe/1/update"))
        .andExpect(status().isOk())
        .andExpect(view().name("recipe/recipeform"))
        .andExpect(model().attributeExists("recipe"));
  }

  @Test
  void testDelete() throws Exception {
    mockMvc.perform(get("/recipe/1/delete"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/"));

    verify(recipeService, times(1)).deleteById(anyLong());
  }

}
