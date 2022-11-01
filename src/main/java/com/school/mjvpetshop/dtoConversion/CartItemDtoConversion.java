package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;

import java.math.BigDecimal;

public class CartItemDtoConversion {

    public static CartItemEntity requestToEntity(CartEntity cart, ProductEntity product, BigDecimal quantity) {
        return new CartItemEntity(cart, product, quantity);
    }

    public static CartItemResponse entityToResponse(CartItemEntity entity) {
        return new CartItemResponse(entity.getProduct(), entity.getQuantity());
    }

}
