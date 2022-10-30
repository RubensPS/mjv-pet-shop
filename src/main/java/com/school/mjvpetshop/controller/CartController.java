package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findCartById(@PathVariable Long id) {
        CartResponse response = cartService.findCartById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/empty/{id}")
    public ResponseEntity<CartResponse> emptyCart(@PathVariable Long id) {
        CartResponse response = cartService.emptyCart(id);
        return ResponseEntity.ok(response);
    }
}
