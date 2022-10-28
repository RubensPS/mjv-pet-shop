package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CustomerDtoConversion;
import com.school.mjvpetshop.exception.CustomerAlreadyExistsException;
import com.school.mjvpetshop.exception.CustomerNotFoundException;
import com.school.mjvpetshop.exception.InvalidCustomerCpfException;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;
import com.school.mjvpetshop.repository.CustomerRepository;
import com.school.mjvpetshop.utilities.PetShopFieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponse saveNewCustomer(CustomerRequest request) throws InvalidCustomerCpfException {
        request.setCpf(PetShopFieldValidator.formatCpf(request.getCpf()));
        if (!PetShopFieldValidator.checkCpf(request.getCpf()))
            throw new InvalidCustomerCpfException("The cpf must contain 11 digits.");
        if (customerRepository.existsByCpf(request.getCpf()) || customerRepository.existsByUserName(request.getUserName()) || customerRepository.existsByEmail(request.getEmail()))
            throw new CustomerAlreadyExistsException("Cpf, userName or email already in database.");
        CustomerEntity entity = CustomerDtoConversion.requestToEntity(request);
        return CustomerDtoConversion.entityToResponse(customerRepository.save(entity));
    }

    public CustomerResponse findCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("A customer with the provided ID doesn't exist in the database."));
        return CustomerDtoConversion.entityToResponse(entity);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerDtoConversion::entityToResponse).toList();
    }

    public ResponseEntity<String> deleteCustomer(Long id) {
        CustomerEntity entity = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("There's no customer with the provided ID in the database to be deleted."));
        customerRepository.delete(entity);
        return ResponseEntity.ok("The customer was deleted from the database.");
    }

}
