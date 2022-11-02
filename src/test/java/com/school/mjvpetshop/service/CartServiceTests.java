package com.school.mjvpetshop.service;

import com.school.mjvpetshop.exception.cart.CartNotFoundException;
import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.CartRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartService cartService;

    private CartEntity cartEntity;
    private CartItemEntity cartItem;
    private ProductEntity product;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setup() {
        product = new ProductEntity(1L, "test product", "test for cart service", BigDecimal.TEN, BigDecimal.valueOf(5), ZonedDateTime.now(), ZonedDateTime.now());
        cartItem = new CartItemEntity(1L, cartEntity, product, BigDecimal.valueOf(5));
        cartEntity = new CartEntity(1L, Set.of(cartItem), BigDecimal.ZERO);
    }

    @DisplayName("unit test for findCartById service ")
    @Test
    void givenCartId_whenFindById_thenReturnCartResponse() {
        given(cartRepository.findById(cartEntity.getId())).willReturn(Optional.of(cartEntity));
        given(cartRepository.save(any(CartEntity.class))).willReturn(cartEntity);
        cartService = new CartService(cartRepository, cartItemRepository);
        CartResponse response = cartService.findCartById(cartEntity.getId());

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(BigDecimal.valueOf(50), cartEntity.getTotalShopValue());
    }

    @DisplayName("unit test for updateTotal service ")
    @Test
    void givenCartId_whenUpdateTotal_thenReturnVoid() {
        given(cartRepository.findById(cartEntity.getId())).willReturn(Optional.of(cartEntity));
        given(cartRepository.save(any(CartEntity.class))).willReturn(cartEntity);
        cartService = new CartService(cartRepository, cartItemRepository);
        BigDecimal total = cartService.updateTotal(cartEntity.getId());

        assertNotEquals(total, BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(50), total);
    }

    @DisplayName("unit test for checkCart service ")
    @Test
    void givenCartId_whenCheckCart_thenThrowException() {
    CartNotFoundException exception = assertThrows(CartNotFoundException.class, () -> cartService.checkCart(cartEntity.getId()));
    assertEquals("A cart with the provided ID doesn't exist in the database.", exception.getMessage());
    }

    @DisplayName("unit test for emptyCart service ")
    @Test
    void givenCartId_whenEmptyCart_thenReturnCartResponse() {
        given(cartRepository.existsById(cartEntity.getId())).willReturn(true);
        given(cartRepository.findById(cartEntity.getId())).willReturn(Optional.of(cartEntity));
        given(cartItemRepository.findAllByCartId(cartEntity.getId())).willReturn(cartEntity.getItems());
        given(cartRepository.save(any(CartEntity.class))).willReturn(cartEntity);
        cartService = new CartService(cartRepository, cartItemRepository);
        cartService.emptyCart(cartEntity.getId());

        verify(cartItemRepository, times(1)).deleteAll(any());
    }


}
