package com.ecommerce.Order.repository;

import com.ecommerce.Order.model.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepo extends JpaRepository<order, Integer> {

}
