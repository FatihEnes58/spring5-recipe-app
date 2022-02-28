package guru.springframework.spring5recipeapp.services;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.spring5recipeapp.converts.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converts.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock
  RecipeRepository recipeRepository;

  @Mock
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Mock
  RecipeToRecipeCommand recipeToRecipeCommand;

  @BeforeEach
  void setUp() {

    MockitoAnnotations.openMocks(this);

    recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe,
        recipeToRecipeCommand);

  }

  @Test
  void getRecipes() {

    Recipe recipe = new Recipe();
    HashSet<Recipe> recipesData = new HashSet<>();
    recipesData.add(recipe);

    Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(1, recipes.size());
    Mockito.verify(recipeRepository, Mockito.times(1)).findAll();

  }
}