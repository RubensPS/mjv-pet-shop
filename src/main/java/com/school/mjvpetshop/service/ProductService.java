package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.ProductDtoConversion;
import com.school.mjvpetshop.exception.product.ProductAlreadyExistsException;
import com.school.mjvpetshop.exception.product.ProductNotFoundException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.CartRepository;
import com.school.mjvpetshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public ProductResponse saveNewProduct(ProductRequest request) {
        if (Boolean.TRUE.equals(productRepository.existsByName(request.getName())))
            throw new ProductAlreadyExistsException(String.format("A product with the name %s already exists in database.", request.getName()));
        ProductEntity entity = ProductDtoConversion.requestToEntity(request);
        return ProductDtoConversion.entityToResponse(productRepository.save(entity));
    }

    public ProductResponse findProductById(Long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
        return ProductDtoConversion.entityToResponse(entity);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
        ProductEntity updatedEntity = ProductDtoConversion.updateEntity(entity, request);
        return ProductDtoConversion.entityToResponse(productRepository.save(updatedEntity));
    }

    public ProductEntity getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
    }

    public ResponseEntity<String> deleteProduct(Long productId) {
        checkProduct(productId);
        List<Long> cartItens = findAllCartItemsByProductId(productId);
        deleteAllCartItemsById(cartItens);
        productRepository.deleteById(productId);
        return ResponseEntity.ok("The product was deleted from database.");
    }

    public void checkProduct(Long productId) {
        if (!productRepository.existsById(productId))
            throw new ProductNotFoundException("A product with the provided ID doesn't exist in the database.");
    }

    public List<Long> findAllCartItemsByProductId(Long productId) {
        return cartItemRepository.findAll()
                .stream().filter(item -> item.getProduct().getId().equals(productId))
                .map(CartItemEntity::getId).toList();
    }

    public void deleteAllCartItemsById(List<Long> cartItens) {
        cartItemRepository.deleteAllById(cartItens);
    }

}
