package com.ecommerce.Customer.dto;

import com.ecommerce.Customer.model.Address;

public record customerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) { }
