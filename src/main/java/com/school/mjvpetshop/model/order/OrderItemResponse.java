package com.school.mjvpetshop.model.order;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;

    private Long orderId;

    private Long productId;

    private BigDecimal productPrice;

    public OrderItemResponse(Long id, Long orderId, Long productId, BigDecimal productPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
    }

}
