package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartItemDtoConversion;
import com.school.mjvpetshop.exception.cartItem.CartItemAlreadyExistsException;
import com.school.mjvpetshop.exception.cart.CartNotFoundException;
import com.school.mjvpetshop.exception.product.InsuficientInventoryException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartService cartService;


    public CartItemResponse addItem(CartItemRequest request) {
        if(!cartService.checkCart(request.getCartId()))
            throw new CartNotFoundException("A cart with the provided ID doesn't exist in the database.");
        ProductEntity product = productService.getProductEntity(request.getProductId());
        if (cartItemRepository.existsByCartIdAndProduct(request.getCartId(), product))
            throw new CartItemAlreadyExistsException("The item is already in the cart.");
        if(request.getQuantity().compareTo(product.getInventory()) > 0)
            throw new InsuficientInventoryException("There's not enough items in the inventory to be sold.");
        CartItemEntity entity = cartItemRepository.save(CartItemDtoConversion.requestToEntity(request, product));
        cartService.updateTotal(request.getCartId());
        return CartItemDtoConversion.entityToResponse(entity);
    }

    public ResponseEntity<String> removeItem(Long cartId, Long productId) {
        if(!cartService.checkCart(cartId))
            throw new CartNotFoundException("A cart with the provided ID doesn't exist in the database.");
        ProductEntity product = productService.getProductEntity(productId);
        Set<CartItemEntity> shopList = cartItemRepository.findAllByCartId(cartId);
        shopList.stream().filter(elemMatch -> elemMatch.getProduct().getId().equals(productId)).forEach(cartItemRepository::delete);
        return ResponseEntity.ok(String.format("%s removed from cart.", product.getName()));
    }

}
