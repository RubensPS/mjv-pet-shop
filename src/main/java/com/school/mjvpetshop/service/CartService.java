package com.school.mjvpetshop.service;

import com.school.mjvpetshop.exception.cart.CartNotFoundException;
import com.school.mjvpetshop.exception.cart.CartUpdateTotalValueException;
import com.school.mjvpetshop.exception.cart.EmptyCartException;
import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartResponse findCartById(Long cartId) {
        updateTotal(cartId);
        CartEntity entity = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        return entityToResponse(entity);
    }

    public void updateTotal(Long cartId) {
        CartEntity entity = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        Set<CartItemEntity> itemList = entity.getItems();
        if (itemList.isEmpty()) {
            entity.setTotalShopValue(BigDecimal.ZERO);
            cartRepository.save(entity);
            return;
        }
        BigDecimal total = itemList.stream()
                .map(item -> item.getProduct().getPrice().multiply(item.getQuantity()))
                .reduce(BigDecimal::add).orElseThrow(() -> new CartUpdateTotalValueException("Cannot update cart items total value."));
        entity.setTotalShopValue(total);
        cartRepository.save(entity);
    }

    public void checkCart(Long id) {
        if(!cartRepository.existsById(id))
            throw new CartNotFoundException("A cart with the provided ID doesn't exist in the database.");
    }

    public CartResponse emptyCart(Long cartId) {
        checkCart(cartId);
        Set<CartItemEntity> cartItems = cartItemRepository.findAllByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new EmptyCartException("The cart is already empty.");
        }
        cartItemRepository.deleteAll(cartItems);
        return findCartById(cartId);
    }

    public CartResponse entityToResponse(CartEntity entity) {
        Set<CartItemResponse> items = entity.getItems().stream().map(this::cartItemEntityToResponse).collect(Collectors.toSet());
        return new CartResponse(entity.getId(), items, entity.getTotalShopValue());
    }

    public CartItemResponse cartItemEntityToResponse(CartItemEntity entity) {
        return new CartItemResponse(entity.getProduct(), entity.getQuantity());
    }

}
