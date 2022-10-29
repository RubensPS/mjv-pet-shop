package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/addItem")
    public ResponseEntity<CartItemResponse> addItemToCart(@RequestBody CartItemRequest request) {
        CartItemResponse response = cartItemService.addItemToCart(request);
        return ResponseEntity.ok(response);
    }


}
