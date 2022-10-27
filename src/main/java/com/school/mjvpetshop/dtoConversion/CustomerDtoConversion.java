package com.school.mjvpetshop.dtoConversion;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;

public class CustomerDtoConversion {

    public static CustomerEntity requestToEntity(CustomerRequest request) {
        return new CustomerEntity(request);
    }


}
