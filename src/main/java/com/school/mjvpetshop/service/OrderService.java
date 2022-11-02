package com.school.mjvpetshop.service;

import com.school.mjvpetshop.exception.cart.EmptyCartException;
import com.school.mjvpetshop.exception.customer.CustomerNotFoundException;
import com.school.mjvpetshop.exception.order.OrderNotFoundException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.customer.CustomerEntity;
import com.school.mjvpetshop.model.order.OrderEntity;
import com.school.mjvpetshop.model.order.OrderResponse;
import com.school.mjvpetshop.model.orderItem.OrderItemEntity;
import com.school.mjvpetshop.model.orderItem.OrderItemResponse;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.CustomerRepository;
import com.school.mjvpetshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public OrderResponse generateNewOrder(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("There's no customer with the provided ID in the database to be deleted."));
        if(customer.getCart().getItems().isEmpty())
            throw new EmptyCartException("Cannot close an order with an empty cart.");
        Set<CartItemEntity> cartItems = cartItemRepository.findAllByCartId(customer.getCart().getId());
        checkInventoryOrder(cartItems);
        OrderEntity order = orderRepository.save(new OrderEntity(customer));
        order.getOrderItems().addAll(cartToOrder(order.getId(), customer.getCart().getItems()));
        Optional<BigDecimal> total = order.getOrderItems().stream().map(item -> item.getProduct().getPrice().multiply(item.getQuantity())).reduce(BigDecimal::add);
        order.setTotalOrderCost(total.get());
        orderRepository.save(order);
        cartItemRepository.deleteAll(cartItems);
        updateInventoryOrder(cartItems);
        return orderEntityToResponse(orderRepository.findById(order.getId()).get());
    }

    public void checkInventoryOrder(Set<CartItemEntity> cartItems) {
        cartItems.forEach(cartItemService::checkInventoryItem);
    }

    public void updateInventoryOrder(Set<CartItemEntity> cartItems) {
        cartItems.forEach(productService::updateInventoryfromOrderQuantity);
    }

    public OrderResponse updateDeliverStatusToTrue(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("There's no order with the ID provided in the database."));
        order.setIsDelivered(true);
        return orderEntityToResponse(orderRepository.save(order));
    }

    public Page<OrderResponse> findAllCustomerOrders(Long customerId, boolean status, Integer pageNumber, Integer pageSize, String sortBy) {
        if(!customerRepository.existsById(customerId))
            throw new CustomerNotFoundException("The customer is not in the database.");
        PageRequest request = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<OrderEntity> pageResult = orderRepository.findAllByCustomerId(customerId, status, request);
        return pageResult.map(this::orderEntityToResponse);
    }

    public List<OrderItemEntity> cartToOrder(Long orderId, Set<CartItemEntity> cartItens) {
        return cartItens.stream().map(item -> new OrderItemEntity(orderId, item.getProduct(), item.getQuantity())).toList();
    }

    public OrderResponse orderEntityToResponse(OrderEntity entity) {
        return new OrderResponse(
                entity.getId(),
                entity.getCustomer().getId(),
                entity.getDeliverDeadLine(),
                entity.getIsDelivered(),
                entity.getOrderItems().stream().map(this::orderItemEntityToResponse).toList(),
                entity.getOrderItems().stream().map(item -> item.getQuantity()).reduce(BigDecimal::add).get(),
                entity.getTotalOrderCost()
        );
    }

    public OrderItemResponse orderItemEntityToResponse(OrderItemEntity entity) {
        return new OrderItemResponse(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getQuantity(),
                entity.getProduct().getPrice()
        );
    }
}
