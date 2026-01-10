package tdtu.edu.vn.SpringCommerce.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.vn.SpringCommerce.models.Cart;
import tdtu.edu.vn.SpringCommerce.services.CartsService;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartsController {

    final CartsService cartsService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam int cartId) {
        try {
            Cart cart = cartsService.getCart(cartId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Cart> addToCart(
            @RequestParam int cartId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        try {
            Cart cart = cartsService.addToCart(cartId, productId, quantity);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Cart> removefromCart(
            @RequestParam int cartId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        try {
            Cart cart = cartsService.removeFromCart(cartId, productId, quantity);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}