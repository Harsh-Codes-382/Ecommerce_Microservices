package com.ecommerce.Customer.DTO;

import com.ecommerce.Customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record customerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) { }
