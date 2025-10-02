package com.ecomerce.Product.model.dto;

import java.math.BigDecimal;

public record productQuantityDTO(
        Integer id,
        double availableQuantity,
        String name,
        String description,
        BigDecimal price
) {}
