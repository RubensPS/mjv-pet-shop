package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartItemDtoConversion;
import com.school.mjvpetshop.exception.cartItem.CartItemAlreadyExistsException;
import com.school.mjvpetshop.exception.cartItem.CartItemNotFoundException;
import com.school.mjvpetshop.exception.product.InsuficientInventoryException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartService cartService;


    public CartItemResponse addItem(CartItemRequest request) {
        cartService.checkCart(request.getCartId());
        ProductEntity product = productService.getProductEntity(request.getProductId());
        if (cartItemRepository.existsByCartIdAndProduct(request.getCartId(), product))
            throw new CartItemAlreadyExistsException("The item is already in the cart.");
        checkInventory(request);
        CartItemEntity entity = cartItemRepository.save(CartItemDtoConversion.requestToEntity(request, product));
        cartService.updateTotal(request.getCartId());
        return CartItemDtoConversion.entityToResponse(entity);
    }

    public ResponseEntity<String> removeItem(Long cartId, Long productId) {
        cartService.checkCart(cartId);
        ProductEntity product = productService.getProductEntity(productId);
        Set<CartItemEntity> shopList = cartItemRepository.findAllByCartId(cartId);
        shopList.stream().filter(item -> item.getProduct().getId().equals(productId)).forEach(cartItemRepository::delete);
        cartService.updateTotal(cartId);
        return ResponseEntity.ok(String.format("%s removed from cart.", product.getName()));
    }

    public CartItemResponse changeCartItemQuantity(CartItemRequest request) {
        cartService.checkCart(request.getCartId());
        productService.checkProduct(request.getProductId());
        List<Long> shopList = cartItemRepository.findAllByCartId(request.getCartId())
                .stream().filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .map(CartItemEntity::getId)
                .toList();
        checkCartItem(shopList);
        Long cartItemId = shopList.get(0);
        CartItemEntity entity = cartItemRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException("A cartItem with the provided ID doesn't exist in the database."));
        entity.setQuantity(request.getQuantity());
        CartItemResponse response = CartItemDtoConversion.entityToResponse(cartItemRepository.save(entity));
        cartService.updateTotal(request.getCartId());
        return response;
    }

    public void checkInventory(CartItemRequest request) {
        if (request.getQuantity().compareTo(productService.getProductEntity(request.getProductId()).getInventory()) > 0)
            throw new InsuficientInventoryException("There's not enough items in the inventory to be sold.");
    }

    public void checkCartItem(List<Long> shopList) {
        if (shopList.isEmpty())
            throw new CartItemNotFoundException("CANNOT UPDATE AMOUNT: the item is not in the cart.");
    }

}
