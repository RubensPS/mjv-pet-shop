package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import com.school.mjvpetshop.model.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    Boolean existsByCartIdAndProduct(Long cartId, ProductEntity productEntity);
}
