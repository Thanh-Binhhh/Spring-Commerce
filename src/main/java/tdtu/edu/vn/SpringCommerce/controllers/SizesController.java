package tdtu.edu.vn.SpringCommerce.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.edu.vn.SpringCommerce.models.Size;
import tdtu.edu.vn.SpringCommerce.services.SizeService;

import java.util.Collection;

@RestController
@RequestMapping("/sizes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizesController {

    final SizeService sizeService;

    @GetMapping
    public ResponseEntity<Iterable<Size>> getSizes() {
        try {
            Iterable<Size> sizes = sizeService.getSizes();
            if (sizes instanceof Collection<?> collection && collection.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(sizes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
