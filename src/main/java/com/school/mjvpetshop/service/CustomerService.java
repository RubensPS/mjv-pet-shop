package com.school.mjvpetshop.service;

import com.school.mjvpetshop.exception.customer.CustomerAlreadyExistsException;
import com.school.mjvpetshop.exception.customer.CustomerNotFoundException;
import com.school.mjvpetshop.exception.customer.InvalidCustomerCpfException;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;
import com.school.mjvpetshop.repository.CustomerRepository;
import com.school.mjvpetshop.utilities.PetShopFieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
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
        CustomerEntity entity = requestToEntity(request);
        return entityToResponse(customerRepository.save(entity));
    }

    public CustomerResponse findCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("A customer with the provided ID doesn't exist in the database."));
        return entityToResponse(entity);
    }

    public List<CustomerResponse> findAllCustomers() {
        List<CustomerEntity> responses = customerRepository.findAll();
        return responses.stream().map(this::entityToResponse).toList();
    }

    public ResponseEntity<String> deleteCustomer(Long id) {
        CustomerEntity entity = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("There's no customer with the provided ID in the database to be deleted."));
        customerRepository.delete(entity);
        return ResponseEntity.ok("The customer was deleted from the database.");
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest request) throws InvalidCustomerCpfException {
        request.setCpf(PetShopFieldValidator.formatCpf(request.getCpf()));
        if (!PetShopFieldValidator.checkCpf(request.getCpf()))
            throw new InvalidCustomerCpfException("The cpf must contain 11 digits.");
        CustomerEntity entity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("A customer with the provided ID doesn't exist in the database."));
        CustomerEntity updatedEntity = updateEntity(entity, request);
        return entityToResponse(customerRepository.save(updatedEntity));
    }

    public CustomerEntity requestToEntity(CustomerRequest request) {
        return new CustomerEntity(request.getFullName(), request.getUserName(), request.getCpf(), request.getEmail());
    }

    public CustomerResponse entityToResponse(CustomerEntity entity) {
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

    public CustomerEntity updateEntity(CustomerEntity entity, CustomerRequest request) {
        entity.setCpf(request.getCpf());
        entity.setUserName(request.getUserName());
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.setUpdateDate(ZonedDateTime.now());
        return entity;
    }
}
