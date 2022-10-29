package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Boolean existsByName(String name);

}
