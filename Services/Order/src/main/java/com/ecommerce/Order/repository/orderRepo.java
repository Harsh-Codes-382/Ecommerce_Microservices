package com.ecommerce.Order.repository;

import com.ecommerce.Order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface orderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomerId(String customerId);
}
