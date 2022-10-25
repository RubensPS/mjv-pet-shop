package com.school.mjvpetshop.model.customer;

import java.time.ZonedDateTime;

public class CustomerEntity {
    private Long id;
    private String fullName;
    private String userName;
    private Long cpf;
    private Telephone phoneNumber;
    private String email;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;
    private Cart shopList;

}
