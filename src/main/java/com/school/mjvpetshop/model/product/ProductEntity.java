package com.school.mjvpetshop.model.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "inventory")
    private BigDecimal inventory;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "update_date")
    private ZonedDateTime updateDate;

    public ProductEntity(String name, String description, BigDecimal price, BigDecimal inventory) {
        this.name = name;
        this.description = description;
        this. price = price;
        this.inventory = inventory;
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
    }
}
