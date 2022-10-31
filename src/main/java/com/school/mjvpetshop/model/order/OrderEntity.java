package com.school.mjvpetshop.model.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long userId;

    private ZonedDateTime creationDate;

    private ZonedDateTime deliverDeadLine;

    private Boolean isDelivered;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<OrderItemEntity> orderItens;

    private BigDecimal totalOrderCost;

    public OrderEntity(Long userId, List<OrderItemEntity> orderItens, BigDecimal totalOrderCost) {
        this.userId = userId;
        this.creationDate = ZonedDateTime.now();
        this.deliverDeadLine = ZonedDateTime.now().plusDays(7);
        this.isDelivered = false;
        this.orderItens = orderItens;
        this.totalOrderCost = totalOrderCost;
    }

}
