package com.school.mjvpetshop.model.orderItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {

    private Long id;

    private Long productId;

    private BigDecimal quantity;

    private BigDecimal productPrice;

    public OrderItemResponse(Long id, Long productId, BigDecimal quantity, BigDecimal productPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

}
