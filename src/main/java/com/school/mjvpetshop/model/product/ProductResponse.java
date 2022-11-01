package com.school.mjvpetshop.model.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;

@Setter
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal inventory;
    private ZonedDateTime updateDate;

    public ProductResponse(Long id, String name, String description, BigDecimal price, BigDecimal inventory, ZonedDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory.setScale(0, RoundingMode.HALF_EVEN);
        this.updateDate = updateDate;
    }

}
