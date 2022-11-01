package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.order.OrderResponse;
import com.school.mjvpetshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/deliver/{orderId}")
    public ResponseEntity<OrderResponse>  updateDeliverStatus(@PathVariable Long orderId) {
        OrderResponse response = orderService.updateDeliverStatusToTrue(orderId);
        return ResponseEntity.ok(response);
    }

}
