package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;

public class CartItemDtoConversion {

    public static CartItemEntity requestToEntity(CartItemRequest request, ProductEntity product) {
        return new CartItemEntity(request.getCartId(), product, request.getQuantity());
    }

    public static CartItemResponse entityToResponse(CartItemEntity entity) {
        return new CartItemResponse(entity.getProduct(), entity.getQuantity());
    }

}
