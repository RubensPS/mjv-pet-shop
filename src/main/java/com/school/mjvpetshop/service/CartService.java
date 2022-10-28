package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartDtoConversion;
import com.school.mjvpetshop.exception.CartNotFoundException;
import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cart.CartResponse;
import com.school.mjvpetshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public CartResponse findCartById(Long id) {
        CartEntity entity = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        return CartDtoConversion.entityToResponse(entity);
    }

}
