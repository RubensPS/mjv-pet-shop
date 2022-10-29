package com.school.mjvpetshop.model.cartItem;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemRequest {
    private Long cartId;
    private Long productId;
    private BigDecimal quantity;

    public CartItemRequest(Long cartId, Long productId, BigDecimal quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

}
