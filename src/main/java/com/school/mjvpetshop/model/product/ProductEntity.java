package com.school.mjvpetshop.model.product;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;
}
