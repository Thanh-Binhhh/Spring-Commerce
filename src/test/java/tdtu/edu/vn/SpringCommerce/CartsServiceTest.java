package tdtu.edu.vn.SpringCommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tdtu.edu.vn.SpringCommerce.models.Cart;
import tdtu.edu.vn.SpringCommerce.models.CartItem;
import tdtu.edu.vn.SpringCommerce.repositories.CartsRepository;
import tdtu.edu.vn.SpringCommerce.services.CartsService;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartsServiceTest {
    @Mock
    private CartsRepository cartsRepository;

    @InjectMocks
    private CartsService cartsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartFound() {
        Cart cart = Cart.builder().id(1).products(new ArrayList<>()).build();
        when(cartsRepository.findById(1)).thenReturn(Optional.of(cart));

        Cart result = cartsService.getCart(1);

        assertThat(result).isEqualTo(cart);
    }

    @Test
    void testGetCartNotFound() {
        when(cartsRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cartsService.getCart(99))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cart not found");
    }

    @Test
    void testAddToCart_NewCart() {
        when(cartsRepository.findById(0)).thenReturn(Optional.empty());

        ArgumentCaptor<Cart> cartCaptor = ArgumentCaptor.forClass(Cart.class);
        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cart result = cartsService.addToCart(0, 101, 2);

        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().get(0).getProductId()).isEqualTo(101);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void testAddToCart_ExistingItem() {
        List<CartItem> items = new ArrayList<>();
        items.add(new CartItem(101, 1));
        Cart existingCart = Cart.builder().id(1).products(items).build();

        when(cartsRepository.findById(1)).thenReturn(Optional.of(existingCart));
        when(cartsRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cart result = cartsService.addToCart(1, 101, 3);

        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(4);
    }

    @Test
    void testRemoveFromCart_EnoughQuantity() {
        CartItem item = new CartItem(101, 5);
        Cart cart = Cart.builder().id(1).products(new ArrayList<>(List.of(item))).build();

        when(cartsRepository.findById(1)).thenReturn(Optional.of(cart));
        when(cartsRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartsService.removeFromCart(1, 101, 2);

        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().get(0).getQuantity()).isEqualTo(3);
    }

    @Test
    void testRemoveFromCart_RemoveAll() {
        CartItem item = new CartItem(101, 3);
        Cart cart = Cart.builder().id(1).products(new ArrayList<>(List.of(item))).build();

        when(cartsRepository.findById(1)).thenReturn(Optional.of(cart));
        when(cartsRepository.save(any(Cart.class))).thenReturn(cart);

        Cart result = cartsService.removeFromCart(1, 101, 3);

        assertThat(result.getProducts()).isEmpty();
    }

    @Test
    void testRemoveFromCart_ProductNotFound() {
        Cart cart = Cart.builder().id(1).products(new ArrayList<>()).build();

        when(cartsRepository.findById(1)).thenReturn(Optional.of(cart));

        assertThatThrownBy(() -> cartsService.removeFromCart(1, 101, 1))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Product not found in cart");
    }
}