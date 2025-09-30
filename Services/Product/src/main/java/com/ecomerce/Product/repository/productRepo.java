package com.ecomerce.Product.repository;


import com.ecomerce.Product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepo extends JpaRepository<Product, Integer> {

}
