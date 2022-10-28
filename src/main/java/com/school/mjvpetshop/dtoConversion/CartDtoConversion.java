package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;

public class CartDtoConversion {

    public static CartResponse entityToResponse(CartEntity entity) {
        return new CartResponse(entity.getId(), entity.getItems(), entity.getTotalShopValue());
    }

}
