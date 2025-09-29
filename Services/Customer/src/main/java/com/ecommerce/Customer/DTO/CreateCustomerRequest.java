package com.ecommerce.Customer.DTO;

import com.ecommerce.Customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequest(
        String id,
        @NotNull(message = "First Name is required")
        String firstname,
        @NotNull(message = "Last Name is required")
        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        Address address
) {}
