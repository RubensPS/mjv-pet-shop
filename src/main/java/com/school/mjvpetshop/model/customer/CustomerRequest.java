package com.school.mjvpetshop.model.customer;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class CustomerRequest {

    private String fullName;
    private String userName;
    private String cpf;
    private String email;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;


    public CustomerRequest(String fullName, String userName, String cpf, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.cpf = cpf;
        this.email = email;
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
    }




}
