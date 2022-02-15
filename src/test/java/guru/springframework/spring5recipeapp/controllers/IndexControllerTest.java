package guru.springframework.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
  void getIndexPage() {

    String viewName = indexController.getIndexPage(model);
    assertEquals("index", viewName);

    Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
    Mockito.verify(model, Mockito.times(1))
        .addAttribute(Mockito.eq("recipes"), Mockito.anySet());
  }
}