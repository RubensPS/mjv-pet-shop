package com.school.mjvpetshop.exception;

import java.util.zip.DataFormatException;

public class InvalidCustomerCpfException extends DataFormatException {
    public InvalidCustomerCpfException(String message) {
        super(message);
    }
}
