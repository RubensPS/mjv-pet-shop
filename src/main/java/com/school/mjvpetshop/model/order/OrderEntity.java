package com.school.mjvpetshop.model.order;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "order_entity")
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerId;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "deliver_limit")
    private ZonedDateTime deliverDeadLine;

    @Column(name = "deliver_status")
    private Boolean isDelivered;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems;

    @Column(name = "total_cost")
    private BigDecimal totalOrderCost;

    public OrderEntity(Long customerId) {
        this.customerId = getCustomerId();
        this.creationDate = ZonedDateTime.now();
        this.deliverDeadLine = ZonedDateTime.now().plusDays(7);
        this.isDelivered = false;
    }

}
