package com.school.mjvpetshop.service;

import com.school.mjvpetshop.dtoConversion.ProductDtoConversion;
import com.school.mjvpetshop.exception.ProductAlreadyExistsException;
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
        if (productRepository.existsByName(request.getName()))
            throw new ProductAlreadyExistsException(String.format("A product with the name %s already exists in database.", request.getName()));
        ProductEntity entity = ProductDtoConversion.requestToEntity(request);
        return ProductDtoConversion.entityToResponse(productRepository.save(entity));
    }
}