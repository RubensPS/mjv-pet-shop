package com.school.mjvpetshop.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private String fullName;
    private String userName;
    private String cpf;
    private String email;

    public CustomerRequest(String fullName, String userName, String cpf, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.cpf = cpf;
        this.email = email;
    }

}
