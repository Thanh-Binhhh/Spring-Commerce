package tdtu.edu.vn.SpringCommerce.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.vn.SpringCommerce.models.Characteristic;
import tdtu.edu.vn.SpringCommerce.services.CharacteristicsService;

import java.util.Collection;

@RestController
@RequestMapping("/characteristics")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CharacteristicsController {

    final CharacteristicsService characteristicsService;

    @GetMapping
    public ResponseEntity<Iterable<Characteristic>> getCategories() {
        try {
            Iterable<Characteristic> categories = characteristicsService.getCharacteristics();
            if (categories instanceof Collection<?> collection && collection.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
