package com.school.mjvpetshop.exception;

import java.util.NoSuchElementException;

public class CartNotFoundException extends NoSuchElementException {

    public CartNotFoundException (String returnMessage) {
        super(returnMessage);
    }

    public CartNotFoundException(String returnMessage, Throwable cause) {
        super(returnMessage, cause);
    }
}
