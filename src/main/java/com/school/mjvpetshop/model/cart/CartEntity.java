package com.school.mjvpetshop.model.cart;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "cart")
@Getter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItemEntity> items;

    @Column(name = "total")
    private BigDecimal totalShopValue;

    public CartEntity() {
        this.items = new HashSet<>();
        this.totalShopValue = BigDecimal.ZERO;
    }

}
