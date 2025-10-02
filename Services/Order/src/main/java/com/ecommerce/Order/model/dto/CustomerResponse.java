package com.ecommerce.Order.model.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {}
