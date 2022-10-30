package com.school.mjvpetshop.model.cartItem;

import com.school.mjvpetshop.model.product.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CartItemResponse {
    private Long productId;
    private BigDecimal price;
    private BigDecimal quantity;

    public CartItemResponse(ProductEntity product, BigDecimal quantity) {
        this.productId = product.getId();
        this.price = product.getPrice();
        this.quantity = quantity;
    }

}
