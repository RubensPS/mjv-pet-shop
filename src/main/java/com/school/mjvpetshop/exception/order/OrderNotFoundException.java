package com.school.mjvpetshop.exception.order;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends NoSuchElementException {
    public OrderNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public OrderNotFoundException(String s) {
        super(s);
    }
}
