package tdtu.edu.vn.SpringCommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.SpringCommerce.models.Category;
import tdtu.edu.vn.SpringCommerce.repositories.CategoriesRepository;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    final CategoriesRepository categoriesRepository;

    public Iterable<Category> getCategories() {
        return categoriesRepository.findAll();
    }
}