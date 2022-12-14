package com.school.mjvpetshop.model.product;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
