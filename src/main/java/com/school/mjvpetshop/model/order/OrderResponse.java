package com.school.mjvpetshop.model.order;

import com.school.mjvpetshop.model.customer.CustomerEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderResponse {

    private Long orderId;

    private CustomerEntity customerId;

    private ZonedDateTime deliverDeadLine;

    private Boolean isDelivered;

    private List<OrderItemResponse> orderItens;

    private Integer totalProducts;

    private BigDecimal totalOrderCost;

    public OrderResponse(Long orderId, CustomerEntity customerId, ZonedDateTime deliverDeadLine, Boolean isDelivered, List<OrderItemResponse> orderItems, Integer totalProducts, BigDecimal totalOrderCost) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliverDeadLine = deliverDeadLine;
        this.isDelivered = isDelivered;
        this.orderItens = orderItems;
        this.totalProducts = totalProducts;
        this.totalOrderCost = totalOrderCost;
    }

}
