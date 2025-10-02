package com.ecommerce.Order.repository;


import com.ecommerce.Order.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepo extends JpaRepository<OrderItem, Integer> {
}
