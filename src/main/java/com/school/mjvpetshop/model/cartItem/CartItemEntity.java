package com.school.mjvpetshop.model.cartItem;

import com.school.mjvpetshop.model.product.ProductEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productId;

    @Column(name = "quantity")
    private BigDecimal quantity;

}
