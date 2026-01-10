package tdtu.edu.vn.SpringCommerce.repositories;

import org.springframework.data.repository.CrudRepository;
import tdtu.edu.vn.SpringCommerce.models.Category;

public interface CategoriesRepository extends CrudRepository<Category, Integer> {
}
