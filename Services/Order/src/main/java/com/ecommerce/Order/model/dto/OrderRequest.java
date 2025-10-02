package com.ecommerce.Order.model.dto;

import com.ecommerce.Order.model.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount cannot be negative")
        BigDecimal amount,
        @NotNull(message = "Payment Method cannot be null")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer is not valid")
        @NotEmpty(message = "Customer is not valid")
        @NotBlank(message = "Customer is not valid")
        String customerId,

        @NotEmpty(message = "Atleast have 1 product to purchase")
        List<ProductToPurchase> products
) {}
