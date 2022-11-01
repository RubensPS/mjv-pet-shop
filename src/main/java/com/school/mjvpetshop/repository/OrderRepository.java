package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.order.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT u FROM OrderEntity u WHERE u.customer.id=?1 and u.isDelivered=?2")
    Page<OrderEntity> findAllByCustomerId(Long customerId, boolean status, Pageable pageable);
}
