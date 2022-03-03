package guru.springframework.spring5recipeapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converts.IngredientToIngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IngredientServiceImplTest {

  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  @Mock
  RecipeRepository recipeRepository;

  IngredientService ingredientService;

  IngredientServiceImplTest(
      IngredientToIngredientCommand ingredientToIngredientCommand) {
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
  }

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
  }

  @Test
  void findByRecipeIdAndIngredientId() {
    //given
    Recipe recipe = new Recipe();
    recipe.setId(1L);

    Ingredient ingredient1 = new Ingredient();
    ingredient1.setId(1L);

    Ingredient ingredient2 = new Ingredient();
    ingredient2.setId(1L);

    Ingredient ingredient3 = new Ingredient();
    ingredient3.setId(3L);

    recipe.addIngredient(ingredient1);
    recipe.addIngredient(ingredient2);
    recipe.addIngredient(ingredient3);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    //then
    IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

    //when
    assertEquals(Long.valueOf(3L), ingredientCommand.getId());
    assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
    verify(recipeRepository, times(1)).findById(anyLong());
  }
}