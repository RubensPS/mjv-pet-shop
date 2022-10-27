package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;
import com.school.mjvpetshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> saveNewCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse response = customerService.saveNewCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable Long id) {
        CustomerResponse response = customerService.findCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        List<CustomerResponse> responses = customerService.findAllCustomers();
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

}
