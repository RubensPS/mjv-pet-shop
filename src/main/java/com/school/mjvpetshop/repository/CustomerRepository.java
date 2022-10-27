package com.school.mjvpetshop.repository;

import com.school.mjvpetshop.model.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
