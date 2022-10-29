package com.school.mjvpetshop.exception;

public class InsuficientInventoryException extends RuntimeException{
    public InsuficientInventoryException(String returnMessage) {
        super(returnMessage);
    }

    public InsuficientInventoryException(String returnMessage, Throwable cause) {
        super(returnMessage, cause);
    }
}
