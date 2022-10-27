package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CustomerDtoConversion;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;
import com.school.mjvpetshop.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse saveNewCustomer(CustomerRequest request) {
        CustomerEntity entity = CustomerDtoConversion.requestToEntity(request);
        return CustomerDtoConversion.entityToResponse(customerRepository.save(entity));
    }

}
