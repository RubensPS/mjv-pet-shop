package com.school.mjvpetshop.model.order;

import com.school.mjvpetshop.model.orderItem.OrderItemResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long orderId;

    private Long customerId;

    private ZonedDateTime deliverDeadLine;

    private Boolean isDelivered;

    private List<OrderItemResponse> orderItens;

    private Integer totalProducts;

    private BigDecimal totalOrderCost;

    public OrderResponse(Long orderId, Long customerId, ZonedDateTime deliverDeadLine, Boolean isDelivered, List<OrderItemResponse> orderItems, Integer totalProducts, BigDecimal totalOrderCost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliverDeadLine = deliverDeadLine;
        this.isDelivered = isDelivered;
        this.orderItens = orderItems;
        this.totalProducts = totalProducts;
        this.totalOrderCost = totalOrderCost.setScale(2, RoundingMode.HALF_EVEN);
    }

}
