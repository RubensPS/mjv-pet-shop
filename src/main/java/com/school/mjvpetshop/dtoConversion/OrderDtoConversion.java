package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.order.OrderEntity;
import com.school.mjvpetshop.model.order.OrderItemEntity;
import com.school.mjvpetshop.model.order.OrderResponse;

import java.util.List;
import java.util.Set;

public class OrderDtoConversion {
    public static List<OrderItemEntity> cartToOrder(Long orderId, Set<CartItemEntity> cartItens) {
        return cartItens.stream().map(item -> new OrderItemEntity(orderId, item.getProduct())).toList();
    }

    public static OrderResponse entityToResponse(OrderEntity entity) {
        return new OrderResponse(
                entity.getId(),
                entity.getCustomerId(),
                entity.getDeliverDeadLine(),
                entity.getIsDelivered(),
                entity.getOrderItems().stream().map(OrderItemDtoConversion::entityToResponse).toList(),
                entity.getOrderItems().size(),
                entity.getTotalOrderCost()
        );
    }
}
