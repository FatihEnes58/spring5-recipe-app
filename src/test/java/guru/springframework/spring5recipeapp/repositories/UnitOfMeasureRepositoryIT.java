package guru.springframework.spring5recipeapp.repositories;

import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;


  @BeforeEach
  void setUp() {
  }

  @Test
  void findByDescription() {

    Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository
        .findByDescription("Teaspoon");

    assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());

  }
}