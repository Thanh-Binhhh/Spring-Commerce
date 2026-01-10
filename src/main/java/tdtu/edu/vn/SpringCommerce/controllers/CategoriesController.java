package tdtu.edu.vn.SpringCommerce.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.SpringCommerce.models.Category;
import tdtu.edu.vn.SpringCommerce.services.CategoriesService;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoriesController {

    final CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> getCategories() {
        try {
            Iterable<Category> categories = categoriesService.getCategories();
            if (categories instanceof Collection<?> collection && collection.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
