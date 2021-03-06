package guru.springframework.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

class IndexControllerTest {

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  IndexController indexController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  public void testMockMVC() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"));
  }


  @Test
  void getIndexPage() {

    //given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());
    recipes.add(new Recipe());

    Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    //when
    String viewName = indexController.getIndexPage(model);

    //then
    assertEquals("index", viewName);

    Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
    Mockito.verify(model, Mockito.times(1))
        .addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}