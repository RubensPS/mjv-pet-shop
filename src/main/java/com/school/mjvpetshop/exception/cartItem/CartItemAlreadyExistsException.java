package com.school.mjvpetshop.exception;

public class CartItemAlreadyExistsException extends RuntimeException{
    public CartItemAlreadyExistsException(String message) {
        super(message);
    }
}
