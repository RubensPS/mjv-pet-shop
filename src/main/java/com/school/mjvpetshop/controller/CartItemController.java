package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.exception.cartItem.CartIdNotProvidedException;
import com.school.mjvpetshop.exception.cartItem.ProductIdNotProvidedException;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/add")
    public ResponseEntity<CartItemResponse> addItemToCart(@RequestBody CartItemRequest request) {
        CartItemResponse response = cartItemService.addItem(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Optional<Long> cartId, @RequestParam Optional<Long> productId) throws CartIdNotProvidedException, ProductIdNotProvidedException {
        return cartItemService.removeItem(
                cartId.orElseThrow(() -> new CartIdNotProvidedException("cartId", "Long", "CartId not provided.")),
                productId.orElseThrow(() -> new ProductIdNotProvidedException("productId", "Long", "ProductId not provided.")));
    }

    @PatchMapping("/quantity")
    public ResponseEntity<CartItemResponse> changeCartItemQuantity(@RequestBody CartItemRequest request) {
        CartItemResponse response = cartItemService.changeCartItemQuantity(request);
        return ResponseEntity.ok(response);
    }

}
