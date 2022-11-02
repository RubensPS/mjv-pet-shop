package com.school.mjvpetshop.model.cartItem;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.product.ProductEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private BigDecimal quantity;

    public CartItemEntity(CartEntity cart, ProductEntity product, BigDecimal quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

}
