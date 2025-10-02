package com.ecomerce.Product.repository;


import com.ecomerce.Product.model.dto.productQuantityDTO;
import com.ecomerce.Product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface productRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.ecomerce.Product.model.dto.productQuantityDTO(p.id, p.availableQuantity, p.name, p.description, p.price)" +
            "from Product p where p.id IN :ids")
    List<productQuantityDTO> fetchProducts(@Param("ids") List<Integer> productIds);    // Projection to fetch only the scalar values

    @Modifying
    @Query("UPDATE Product p SET p.availableQuantity = :qty WHERE id = :id")
    void updateQuantity(@Param("qty") double qty, @Param("id") Integer id);

    @Query("SELECT new com.ecomerce.Product.model.dto.productQuantityDTO(p.id, p.availableQuantity, p.name, p.description, p.price)"
            + "from Product p where p.category.id = :catId")
    List<productQuantityDTO> fetchPrductsBasedOnCategory(
            @Param("catId") Integer id
    );
}
