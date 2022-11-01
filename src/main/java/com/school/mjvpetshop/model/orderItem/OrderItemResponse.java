package com.school.mjvpetshop.model.orderItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        this.quantity = quantity.setScale(0, RoundingMode.HALF_EVEN);
        this.productPrice = productPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

}
