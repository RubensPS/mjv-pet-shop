package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.orderItem.OrderItemEntity;
import com.school.mjvpetshop.model.orderItem.OrderItemResponse;

public class OrderItemDtoConversion {

    public static OrderItemResponse entityToResponse(OrderItemEntity entity) {
        return new OrderItemResponse(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getQuantity(),
                entity.getProduct().getPrice()
        );
    }

}
