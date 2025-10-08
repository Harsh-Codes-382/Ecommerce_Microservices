package com.ecommerce.Order.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductToPurchase(

        @NotNull(message = "Product is compulsory")
        Integer productId,

        @Positive(message = "Quantity cannot be negative")
        double quantity
) {}
