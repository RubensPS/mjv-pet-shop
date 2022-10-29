package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartItemDtoConversion;
import com.school.mjvpetshop.exception.CartItemAlreadyExistsException;
import com.school.mjvpetshop.exception.CartNotFoundException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartService cartService;


    public CartItemResponse addItemToCart(CartItemRequest request) {
        if(!cartService.checkCart(request.getCartId()))
            throw new CartNotFoundException("A cart with the provided ID doesn't exist in the database.");
        ProductEntity product = productService.getProductEntity(request.getProductId());
        if (cartItemRepository.existsByCartIdAndProduct(request.getCartId(), product))
            throw new CartItemAlreadyExistsException("The item is already in the cart.");
        CartItemEntity entity = cartItemRepository.save(CartItemDtoConversion.requestToEntity(request, product));
        cartService.updateTotal(request.getCartId());
        return CartItemDtoConversion.entityToResponse(entity);
    }


}
