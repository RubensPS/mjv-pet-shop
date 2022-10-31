package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
