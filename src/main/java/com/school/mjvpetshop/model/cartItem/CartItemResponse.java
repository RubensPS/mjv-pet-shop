package com.school.mjvpetshop.model.cartItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartItemResponse {
    private Long productId;
    private BigDecimal quantity;

    public CartItemResponse(Long productId, BigDecimal quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
