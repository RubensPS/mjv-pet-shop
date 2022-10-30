package com.school.mjvpetshop.exception.cartItem;

import java.util.NoSuchElementException;

public class CartItemNotFoundException extends NoSuchElementException {
    public CartItemNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public CartItemNotFoundException(String s) {
        super(s);
    }
}
