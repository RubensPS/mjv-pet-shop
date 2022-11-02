package com.school.mjvpetshop;

import com.school.mjvpetshop.exception.customer.InvalidCustomerCpfException;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.customer.CustomerRequest;
import com.school.mjvpetshop.model.customer.CustomerResponse;
import com.school.mjvpetshop.repository.CustomerRepository;

import com.school.mjvpetshop.service.CustomerService;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeAll
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    private CustomerEntity customerEntity = new CustomerEntity(1L,"Rubens Souza", "rps", "12345678900", "rps@email.com");
    private CustomerEntity customerUpdated = new CustomerEntity("Nome Alterado", "souza", "12345678900", "rps@email.com");
    private CustomerResponse customerResponse = new CustomerResponse(
            1L, "Rubens Souza", "rps", "12345678900", "rps@email.com", ZonedDateTime.now(), ZonedDateTime.now(), 1L);

    @DisplayName("unit test for saveNewCustomer service ")
    @Test
    void givenCustomerRequest_whenSaveNewCustomer_thenReturnCustomerResponse() throws InvalidCustomerCpfException {
        CustomerRequest request = new CustomerRequest("Rubens Souza", "rps", "12345678900", "rps@email.com");
        given(customerRepository.existsByCpf(request.getCpf())).willReturn(false);
        given(customerRepository.existsByUserName(request.getUserName())).willReturn(false);
        given(customerRepository.existsByEmail(request.getEmail())).willReturn(false);
        given(customerRepository.save(any(CustomerEntity.class))).willReturn(customerEntity);
        CustomerResponse response = customerService.saveNewCustomer(request);

        assertNotNull(response);
    }


    @DisplayName("unit test for findCustomerById service ")
    @Test
    void givenCustomerId_whenFindCustomerById_thenReturnCustomerResponse() {
        when(customerService.findCustomerById(customerEntity.getId())).thenReturn(customerService.entityToResponse(customerEntity));
        when(customerService.requestToEntity(any(CustomerRequest.class))).thenReturn(customerEntity);
        when(customerService.entityToResponse(any(CustomerEntity.class))).thenReturn(customerResponse);
        CustomerResponse response = customerService.findCustomerById(customerEntity.getId());

        assertEquals("Rubens Souza", response.getFullName());
        assertEquals("rps", response.getUserName());
        assertEquals("rps@email.com", response.getEmail());
        assertNotNull(response);
    }

    @DisplayName("unit test for findAllCustomers service ")
    @Test
    void givenNothing_whenFindAll_thenReturnListOfCustomerResponse() {
        when(customerRepository.findAll()).thenReturn(List.of(customerEntity));
        List<CustomerResponse> responses = customerService.findAllCustomers();

        assertEquals(1, responses.size());
        assertEquals("rps", responses.get(0).getUserName());
        assertEquals("rps@email.com", responses.get(0).getEmail());
        assertFalse(responses.isEmpty());
    }

    @DisplayName("unit test for deleteCustomer service ")
    @Test
    void givenCustomerId_whenDeleteUser_thenReturnResponseEntityOfString() {
        ResponseEntity<String> response = customerService.deleteCustomer(customerEntity.getId());

        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerRepository, never()).deleteById(any(Long.TYPE));
    }

    @DisplayName("unit test for updateCustomer service ")
    @Test
    void givenCustomerIdAndRequest_whenUpdateCustomer_thenReturnCustomerResponse() throws InvalidCustomerCpfException {
        CustomerRequest request = new CustomerRequest("Nome Alterado", "souza", "12345678900", "rps@email.com");
        when(customerRepository.findById(customerEntity.getId())).thenReturn(Optional.of(customerEntity));
        given(customerRepository.save(any(CustomerEntity.class))).willReturn(customerUpdated);
        CustomerResponse response = customerService.updateCustomer(customerEntity.getId(),request);

        assertEquals("Nome Alterado", response.getFullName());
        assertEquals("souza", response.getUserName());

    }

}
