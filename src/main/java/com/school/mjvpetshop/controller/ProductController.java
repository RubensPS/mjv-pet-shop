package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;
import com.school.mjvpetshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveNewProduct(@RequestBody ProductRequest request) {
        ProductResponse response = productService.saveNewProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
