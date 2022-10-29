package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartDtoConversion;
import com.school.mjvpetshop.exception.CartNotFoundException;
import com.school.mjvpetshop.exception.CartUpdateTotalValueException;
import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartResponse findCartById(Long id) {
        CartEntity entity = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        return CartDtoConversion.entityToResponse(entity);
    }

    public void updateTotal(Long cartId) {
        CartEntity entity = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        Set<CartItemEntity> itemList = entity.getItems();
        BigDecimal total = itemList.stream()
                .map(item -> item.getProduct().getPrice().multiply(item.getQuantity()))
                .reduce(BigDecimal::add).orElseThrow(() -> new CartUpdateTotalValueException("Cannot update cart items total value."));
        entity.setTotalShopValue(total);
        cartRepository.save(entity);
    }

    public boolean checkCart(Long id) {
        return cartRepository.existsById(id);
    }

}
