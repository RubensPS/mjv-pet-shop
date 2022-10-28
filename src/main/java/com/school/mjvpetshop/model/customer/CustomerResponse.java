package com.school.mjvpetshop.model.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String fullName;
    private String userName;
    private String cpf;
    private String email;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;

    public CustomerResponse(Long id, String fullName, String userName, String cpf, String email, ZonedDateTime creationDate, ZonedDateTime updateDate) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.cpf = cpf;
        this.email = email;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }
}
