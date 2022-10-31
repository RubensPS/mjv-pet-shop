package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.OrderDtoConversion;
import com.school.mjvpetshop.exception.customer.CustomerNotFoundException;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.order.OrderEntity;
import com.school.mjvpetshop.model.order.OrderResponse;
import com.school.mjvpetshop.repository.CustomerRepository;
import com.school.mjvpetshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CartService cartService;

    public OrderResponse generateNewOrder(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("There's no customer with the provided ID in the database to be deleted."));
        OrderEntity order = new OrderEntity(customerId);
        order.setOrderItems(OrderDtoConversion.cartToOrder(order.getId(), customer.getCart().getItems()));
        order.setTotalOrderCost(customer.getCart().getTotalShopValue());
        OrderResponse response = OrderDtoConversion.entityToResponse(orderRepository.save(order));
        cartService.emptyCart(customer.getCart().getId());
        return response;
    }

}
