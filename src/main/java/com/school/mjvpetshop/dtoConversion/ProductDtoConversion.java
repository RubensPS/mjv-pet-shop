package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;

public class ProductDtoConversion {

    public static ProductEntity requestToEntity(ProductRequest request) {
        return new ProductEntity(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory());
    }

    public static ProductResponse entityToResponse(ProductEntity entity) {
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getInventory(),
                entity.getUpdateDate());
    }

}
