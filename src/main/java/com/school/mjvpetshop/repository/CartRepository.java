package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
