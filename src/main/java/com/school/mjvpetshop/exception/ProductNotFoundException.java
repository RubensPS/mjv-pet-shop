package com.school.mjvpetshop.exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException(String s) {
    }

    public ProductNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }
}
