package com.school.mjvpetshop.model.order;

import com.school.mjvpetshop.model.product.ProductEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderResponse {

    private Long orderId;

    private Long userId;

    private ZonedDateTime deliverDeadLine;

    private Boolean isDelivered;

    @ManyToMany(mappedBy = "id")
    @JoinColumn(name = "products")
    private List<ProductEntity> orderItens;

    private Long totalProducts;

    private BigDecimal totalOrderCost;

    public OrderResponse(Long orderId, Long userId, ZonedDateTime deliverDeadLine, Boolean isDelivered, List<ProductEntity> orderItens, Long totalProducts, BigDecimal totalOrderCost) {
        this.orderId = orderId;
        this.userId = userId;
        this.deliverDeadLine = deliverDeadLine;
        this.isDelivered = isDelivered;
        this.orderItens = orderItens;
        this.totalProducts = totalProducts;
        this.totalOrderCost = totalOrderCost;
    }

}
