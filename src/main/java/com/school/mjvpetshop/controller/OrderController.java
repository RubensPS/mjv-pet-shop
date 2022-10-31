package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.order.OrderResponse;
import com.school.mjvpetshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/generate/{customerId}")
    public ResponseEntity<OrderResponse> generateNewOrder(@PathVariable Long customerId){
        OrderResponse response = orderService.generateNewOrder(customerId);
        return ResponseEntity.ok(response);
    }

}
