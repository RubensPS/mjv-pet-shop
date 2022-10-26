package com.school.mjvpetshop.model.telephone;

import com.school.mjvpetshop.model.telephone.enums.PhoneType;

import javax.persistence.*;

@Entity(name = "telephone")
public class TelephoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private PhoneType type;

    @Column(name = "ddd", length = 2)
    private Integer ddd;

    @Column(name = "number", length = 9)
    private Integer number;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Long customerId;

}
