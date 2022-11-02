package com.school.mjvpetshop.model.customer;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.order.OrderEntity;
import com.school.mjvpetshop.model.telephone.TelephoneEntity;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TelephoneEntity> phoneNumbers;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "update_date")
    private ZonedDateTime updateDate;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orderId;


    public CustomerEntity(String fullName, String userName, String cpf, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.cpf = cpf;
        this.phoneNumbers = new ArrayList<>();
        this.email = email;
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
        this.cart = new CartEntity();
    }

    public CustomerEntity(Long id, String fullName, String userName, String cpf, String email) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.cpf = cpf;
        this.phoneNumbers = new ArrayList<>();
        this.email = email;
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
        this.cart = new CartEntity();
    }
}
