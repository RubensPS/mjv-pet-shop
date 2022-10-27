package com.school.mjvpetshop.model.customer;

import java.time.ZonedDateTime;

public class CustomerResponse {
    private Long id;
    private String fullName;
    private String userName;
    private String cpf;
    private String email;
    private ZonedDateTime creationDate;
    private ZonedDateTime updateDate;

    public CustomerResponse(CustomerEntity entity) {
        this.id = entity.getId();
        this.fullName = entity.getFullName();
        this.userName = entity.getUserName();
        this.cpf = entity.getCpf();
        this.email = entity.getEmail();
        this.creationDate = entity.getCreationDate();
        this.updateDate = entity.getUpdateDate();
    }
}
