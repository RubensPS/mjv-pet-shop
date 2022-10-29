package com.school.mjvpetshop.exception;

import java.util.NoSuchElementException;

public class CustomerNotFoundException extends NoSuchElementException {

    public CustomerNotFoundException(String returnMessage) {
        super(returnMessage);
    }

    public CustomerNotFoundException(String returnMessage, Throwable cause) {
        super(returnMessage, cause);
    }

}
