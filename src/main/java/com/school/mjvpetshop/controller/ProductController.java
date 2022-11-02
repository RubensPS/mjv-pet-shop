package com.school.mjvpetshop.controller;

import com.school.mjvpetshop.model.product.ProductRequest;
import com.school.mjvpetshop.model.product.ProductResponse;
import com.school.mjvpetshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        ProductResponse response = productService.findProductById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
//        return productService.deleteProduct(id);
//    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductResponse>> findAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "price") String sortBy
            ) {
        Page<ProductResponse> response = productService.findAllProducts(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(response);
    }

}
