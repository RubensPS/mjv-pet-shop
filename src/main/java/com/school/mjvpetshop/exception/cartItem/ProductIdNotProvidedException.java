package com.school.mjvpetshop.exception.cartItem;

import org.springframework.web.bind.MissingServletRequestParameterException;

public class ProductIdNotProvidedException extends MissingServletRequestParameterException {
    private String message;

    public ProductIdNotProvidedException(String parameterName, String parameterType, String message) {
        super(parameterName, parameterType);
        this.message = message;
    }
}
