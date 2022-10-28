package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;

import java.time.ZonedDateTime;

public class CustomerDtoConversion {

    public static CustomerEntity requestToEntity(CustomerRequest request) {
        return new CustomerEntity(request.getFullName(), request.getUserName(), request.getCpf(), request.getEmail());
    }

    public static CustomerResponse entityToResponse(CustomerEntity entity) {
        return new CustomerResponse(
                entity.getId(),
                entity.getFullName(),
                entity.getUserName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getCreationDate(),
                entity.getUpdateDate(),
                entity.getCart().getId());
    }

    public static CustomerEntity updateEntity(CustomerEntity entity, CustomerRequest request) {
        entity.setCpf(request.getCpf());
        entity.setUserName(request.getUserName());
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.setUpdateDate(ZonedDateTime.now());
        return entity;
    }


}
