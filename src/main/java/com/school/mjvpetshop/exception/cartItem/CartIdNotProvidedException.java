package com.school.mjvpetshop.exception.cartItem;

import org.springframework.web.bind.MissingServletRequestParameterException;

public class CartIdNotProvidedException extends MissingServletRequestParameterException {
    private String message;

    public CartIdNotProvidedException(String parameterName, String parameterType, String message) {
        super(parameterName, parameterType);
        this.message = message;
    }
}
