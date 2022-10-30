package com.school.mjvpetshop.exception.cartItem;

public class CartItemAlreadyExistsException extends RuntimeException{
    public CartItemAlreadyExistsException(String message) {
        super(message);
    }
}
