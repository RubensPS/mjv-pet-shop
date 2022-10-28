package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;

import java.time.ZonedDateTime;

public class CustomerDtoConversion {

    public static CustomerEntity requestToEntity(CustomerRequest request) {
        return new CustomerEntity(request);
    }

    public static CustomerResponse entityToResponse(CustomerEntity entity) {
        return new CustomerResponse(entity);
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
