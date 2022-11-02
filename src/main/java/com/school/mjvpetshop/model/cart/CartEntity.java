package com.school.mjvpetshop.model.cart;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@AllArgsConstructor
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private Set<CartItemEntity> items;

    @Column(name = "total")
    @Setter
    private BigDecimal totalShopValue;

    public CartEntity() {
        this.items = new HashSet<>();
        this.totalShopValue = BigDecimal.ZERO;
    }

}
