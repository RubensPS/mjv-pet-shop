package com.school.mjvpetshop.exception;

import com.school.mjvpetshop.exception.cart.CartNotFoundException;
import com.school.mjvpetshop.exception.cartItem.CartItemAlreadyExistsException;
import com.school.mjvpetshop.exception.customer.CustomerAlreadyExistsException;
import com.school.mjvpetshop.exception.customer.CustomerNotFoundException;
import com.school.mjvpetshop.exception.customer.InvalidCustomerCpfException;
import com.school.mjvpetshop.exception.product.InsuficientInventoryException;
import com.school.mjvpetshop.exception.product.ProductAlreadyExistsException;
import com.school.mjvpetshop.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class PetShopExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = InvalidCustomerCpfException.class)
    public ResponseEntity<Object> handleInvalidCustomerCpfException(InvalidCustomerCpfException e) {
        HttpStatus unprocessableEntity = HttpStatus.UNPROCESSABLE_ENTITY;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                unprocessableEntity,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, unprocessableEntity);
    }

    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<Object> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = CartItemAlreadyExistsException.class)
    public ResponseEntity<Object> handleCartItemAlreadyExistsException(CartItemAlreadyExistsException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = InsuficientInventoryException.class)
    public ResponseEntity<Object> handleInsuficientInventoryException(InsuficientInventoryException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        PetShopApiException apiException = new PetShopApiException(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, conflict);
    }

}
