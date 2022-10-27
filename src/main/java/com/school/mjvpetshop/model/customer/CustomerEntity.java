package com.school.mjvpetshop.model.customer;

import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.telephone.TelephoneEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TelephoneEntity> phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "update_date")
    private ZonedDateTime updateDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

}
