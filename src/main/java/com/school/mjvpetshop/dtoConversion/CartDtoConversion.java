package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class CartDtoConversion {

    public static CartResponse entityToResponse(CartEntity entity) {
        Set<CartItemResponse> items = entity.getItems().stream().map(CartItemDtoConversion::entityToResponse).collect(Collectors.toSet());
        return new CartResponse(entity.getId(), items, entity.getTotalShopValue());
    }

}
