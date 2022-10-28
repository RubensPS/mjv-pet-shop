package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.ProductDtoConversion;
import com.school.mjvpetshop.exception.ProductAlreadyExistsException;
import com.school.mjvpetshop.exception.ProductNotFoundException;
import com.school.mjvpetshop.model.product.ProductEntity;
import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;
import com.school.mjvpetshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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

}
