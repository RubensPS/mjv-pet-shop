package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.OrderDtoConversion;
import com.school.mjvpetshop.exception.cart.EmptyCartException;
import com.school.mjvpetshop.exception.customer.CustomerNotFoundException;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.order.OrderEntity;
import com.school.mjvpetshop.model.order.OrderResponse;
import com.school.mjvpetshop.repository.CustomerRepository;
import com.school.mjvpetshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderResponse generateNewOrder(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("There's no customer with the provided ID in the database to be deleted."));
        if(customer.getCart().getItems().isEmpty())
            throw new EmptyCartException("Cannot close an order with an empty cart.");
        OrderEntity order = orderRepository.save(new OrderEntity(customer));
        order.getOrderItems().addAll(OrderDtoConversion.cartToOrder(order.getId(), customer.getCart().getItems()));
        Optional<BigDecimal> total = order.getOrderItems().stream().map(item -> item.getProduct().getPrice().multiply(item.getQuantity())).reduce(BigDecimal::add);
        order.setTotalOrderCost(total.get());
        return OrderDtoConversion.entityToResponse(orderRepository.save(order));
    }

}
