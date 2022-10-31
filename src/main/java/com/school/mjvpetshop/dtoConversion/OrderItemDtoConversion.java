package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.order.OrderItemEntity;
import com.school.mjvpetshop.model.order.OrderItemResponse;

public class OrderItemDtoConversion {

    public static OrderItemResponse entityToResponse(OrderItemEntity entity) {
        return new OrderItemResponse(
                entity.getId(),
                entity.getOrderId(),
                entity.getProduct().getId(),
                entity.getProduct().getPrice()
        );
    }

}
