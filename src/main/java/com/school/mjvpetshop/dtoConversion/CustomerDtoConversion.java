package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;

public class CustomerDtoConversion {

    public static CustomerEntity requestToEntity(CustomerRequest request) {
        return new CustomerEntity(request);
    }

    public static CustomerResponse entityToResponse(CustomerEntity entity) {
        return new CustomerResponse(entity);
    }


}
