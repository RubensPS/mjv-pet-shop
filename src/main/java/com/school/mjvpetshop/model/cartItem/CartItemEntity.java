package com.school.mjvpetshop.model.cartItem;

import com.school.mjvpetshop.model.product.ProductEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@NoArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private BigDecimal quantity;

    public CartItemEntity(Long cartId, ProductEntity product, BigDecimal quantity) {
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }

}
