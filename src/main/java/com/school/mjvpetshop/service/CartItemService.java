package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.CartItemDtoConversion;
import com.school.mjvpetshop.exception.cart.CartNotFoundException;
import com.school.mjvpetshop.exception.cartItem.CartItemAlreadyExistsException;
import com.school.mjvpetshop.exception.cartItem.CartItemNotFoundException;
import com.school.mjvpetshop.exception.product.InsuficientInventoryException;
import com.school.mjvpetshop.model.cart.CartEntity;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.cartItem.CartItemRequest;
import com.school.mjvpetshop.model.cartItem.CartItemResponse;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final CartRepository cartRepository;

    public CartItemResponse addItem(CartItemRequest request) {
        CartEntity cart = cartRepository.findById(request.getCartId()).orElseThrow(() -> new CartNotFoundException("A cart with the provided ID doesn't exist in the database."));
        ProductEntity product = productService.getProductEntity(request.getProductId());
        if (cartItemRepository.existsByCartIdAndProduct(request.getCartId(), product))
            throw new CartItemAlreadyExistsException("The item is already in the cart.");
        checkInventory(request);
        CartItemEntity entity = cartItemRepository.save(CartItemDtoConversion.requestToEntity(cart, product, request.getQuantity()));
        cartService.updateTotal(request.getCartId());
        return CartItemDtoConversion.entityToResponse(entity);
    }

    public ResponseEntity<String> removeItem(Long cartId, Long productId) {
        cartService.checkCart(cartId);
        ProductEntity product = productService.getProductEntity(productId);
        Set<CartItemEntity> shopList = cartItemRepository.findAllByCartId(cartId);
        List<Long> productIdList = shopList
                .stream().filter(item -> item.getProduct().getId().equals(productId))
                .map(CartItemEntity::getId)
                .toList();
        checkCartItem(productIdList, String.format("CANNOT REMOVE ITEM: %s is not in the chart.", product.getName()));
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
        checkCartItem(shopList, "CANNOT UPDATE AMOUNT: the item is not in the cart.");
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

    public void checkCartItem(List<Long> shopList, String message) {
        if (shopList.isEmpty())
            throw new CartItemNotFoundException(message);
    }

    public void checkInventoryItem(CartItemEntity item) {
        Optional<CartItemEntity> cartItem = cartItemRepository.findById(item.getId());
        if (cartItem.isEmpty())
            throw new CartItemNotFoundException("Item not found in database.");
        if (cartItem.get().getQuantity().compareTo(cartItem.get().getProduct().getInventory()) > 0)
            throw new InsuficientInventoryException(
                    String.format("There's not enough %s in the inventory to close the order. Please romeve this item and try again.", cartItem.get().getProduct().getName()));
    }

}
