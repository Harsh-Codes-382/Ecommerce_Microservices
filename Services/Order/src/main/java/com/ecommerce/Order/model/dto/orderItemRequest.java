package com.ecommerce.Order.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record orderItemRequest(
        Integer orderId,
        @NotNull(message = "Product Not Found or Invalid")
        @Positive(message = "Product Id is not valid")
        Integer productId,
        @Positive(message = "Quantity should be Positive")
        double quantity
) {
}
