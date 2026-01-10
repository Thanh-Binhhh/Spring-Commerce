package tdtu.edu.vn.SpringCommerce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.edu.vn.SpringCommerce.models.Cart;
import tdtu.edu.vn.SpringCommerce.models.CartItem;
import tdtu.edu.vn.SpringCommerce.repositories.CartsRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartsService {

    final CartsRepository cartsRepository;

    public Cart getCart(int cartId) {
        return cartsRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addToCart(int cartId, int productId, int quantity) {
        try {
            Cart cart = cartsRepository.findById(cartId)
                    .orElseGet(() -> {
                        Cart newCart =  Cart
                                        .builder()
                                        .products(new ArrayList<>())
                                        .build();
                        return cartsRepository.save(newCart);
                    });
            System.out.println("HIHIHIHIHIHI" + cart);
            Optional<CartItem> existingItem = cart.getProducts()
                    .stream()
                    .filter(item -> item.getProductId() == productId)
                    .findFirst();

            if (existingItem.isPresent()) {
                existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
            } else {
                cart.getProducts().add(new CartItem(productId, quantity));
            }

            return cartsRepository.save(cart);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Cart removeFromCart(int cartId, Integer productId, int quantity) {
        Cart cart = cartsRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Optional<CartItem> existingItem = cart.getProducts()
                .stream()
                .filter(item -> item.getProductId() == productId)
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();

            // Reduce quantity or remove the product if quantity becomes 0 or less
            if (cartItem.getQuantity() <= quantity) {
                cart.getProducts().remove(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() - quantity);
            }

            cartsRepository.save(cart);
            return cart;

        } else {
            throw new RuntimeException("Product not found in cart");
        }
    }
}
