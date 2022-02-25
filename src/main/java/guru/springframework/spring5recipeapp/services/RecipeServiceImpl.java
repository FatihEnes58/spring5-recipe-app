package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  public RecipeServiceImpl(
      RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  @Override
  public Set<Recipe> getRecipes() {
    Set<Recipe> recipes = new HashSet<>();
    log.debug("I am in the service");
    recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

    return recipes;
  }

  @Override
  public Recipe findById(Long id) {

    Optional<Recipe> recipeOptional = recipeRepository.findById(id);

    if(!recipeOptional.isPresent()) {
      throw new RuntimeException("Recipe not found!");
    }

    return recipeOptional.get();
  }
}
