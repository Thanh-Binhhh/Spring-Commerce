package tdtu.edu.vn.SpringCommerce.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.SpringCommerce.models.Plant;
import tdtu.edu.vn.SpringCommerce.services.PlantsService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plants")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantsController {

    final PlantsService plantsService;

    @GetMapping
    public ResponseEntity<?> getPlants(@RequestParam (required = false) Integer id, @RequestParam (required = false) Integer currentPage) {
        try {
            if (id != null && id > 0) {
                Optional<Plant> plant = plantsService.getPlant(id);
                return plant.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
            }

            Iterable<Plant> plants = plantsService.getPlants(currentPage);
            return plants instanceof Collection<?> collection && collection.isEmpty() ?
                    ResponseEntity.noContent().build()
                    : ResponseEntity.ok(plants);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<?> getPlantsByIds(@RequestParam List<Integer> ids) {
        try {
            List<Plant> plants = plantsService.getPlantsByIds(ids);

            if (plants.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(plants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/price")
    public ResponseEntity<?> getPrice() {
        try {
            int[] prices = plantsService.getPlantsPrice();
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getPlantsByFilter(
            @RequestParam (required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String characteristic,
            @RequestParam(required = false) Integer maxPrice) {
        try {
            List<Plant> plants = plantsService.filterPlants(name, category, size, characteristic, maxPrice);

            if (plants.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(plants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
