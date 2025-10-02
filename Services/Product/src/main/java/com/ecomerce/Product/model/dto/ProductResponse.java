package com.ecomerce.Product.model.dto;

import java.math.BigDecimal;



public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Category category
) {
    public record Category(
            Integer cat_id,
            String name,
            String description
    ){}
}
