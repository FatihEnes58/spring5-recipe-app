package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domain.Category;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

  Optional<Category> findByDescription(String description);

}
