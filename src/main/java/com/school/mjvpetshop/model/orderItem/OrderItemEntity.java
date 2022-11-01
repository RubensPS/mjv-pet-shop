package com.school.mjvpetshop.model.orderItem;

import com.school.mjvpetshop.model.product.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@Setter
@Getter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private BigDecimal quantity;

    public OrderItemEntity(Long orderId, ProductEntity product, BigDecimal quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

}
