package com.school.mjvpetshop.model.product;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal inventory;

    public ProductRequest(String name, String description, BigDecimal price, BigDecimal inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
    }


}
