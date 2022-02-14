package guru.springframework.spring5recipeapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

  Category category;

  @BeforeEach
  public void setUp() {
    category = new Category();
  }

  @Test
  void getId() throws Exception{

    Long idValue = 4L;

    category.setId(4L);

    assertEquals(idValue, category.getId());

  }

  @Test
  void getDescription() {
  }

  @Test
  void getRecipes() {
  }
}