package com.school.mjvpetshop.service;

import com.school.mjvpetshop.exception.product.ProductAlreadyExistsException;
import com.school.mjvpetshop.exception.product.ProductNotFoundException;
import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;
import com.school.mjvpetshop.repository.CartItemRepository;
import com.school.mjvpetshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public ProductResponse saveNewProduct(ProductRequest request) {
        if (Boolean.TRUE.equals(productRepository.existsByName(request.getName())))
            throw new ProductAlreadyExistsException(String.format("A product with the name %s already exists in database.", request.getName()));
        ProductEntity entity = requestToEntity(request);
        return entityToResponse(productRepository.save(entity));
    }

    public ProductResponse findProductById(Long id) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
        return entityToResponse(entity);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        ProductEntity entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
        ProductEntity updatedEntity = updateEntity(entity, request);
        return entityToResponse(productRepository.save(updatedEntity));
    }

    public ProductEntity getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("A product with the provided ID doesn't exist in the database."));
    }

//    public ResponseEntity<String> deleteProduct(Long productId) {
//        checkProduct(productId);
//        List<Long> cartItens = findAllCartItemsByProductId(productId);
//        deleteAllCartItemsById(cartItens);
//        productRepository.deleteById(productId);
//        return ResponseEntity.ok("The product was deleted from database.");
//    }

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

    public Page<ProductResponse> findAllProducts(Integer pageNumber, Integer pageSize, String sortBy) {
        PageRequest request = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<ProductEntity> pageResult = productRepository.findAll(request);
        return pageResult.map(this::entityToResponse);
    }

    public void updateInventoryfromOrderQuantity(CartItemEntity item) {
        ProductEntity product = productRepository.findById(item.getProduct()
                .getId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("%s doesn't exist in the inventry", item.getProduct().getName())));
        product.setInventory(product.getInventory().subtract(item.getQuantity()));
        productRepository.save(product);
    }

    public ProductEntity requestToEntity(ProductRequest request) {
        return new ProductEntity(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory());
    }

    public ProductResponse entityToResponse(ProductEntity entity) {
        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getInventory(),
                entity.getUpdateDate());
    }

    public ProductEntity updateEntity(ProductEntity entity, ProductRequest request) {
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setInventory(request.getInventory());
        entity.setUpdateDate(ZonedDateTime.now());
        return entity;
    }
}
