package com.ecommerce.Customer.mapper;

import com.ecommerce.Customer.dto.CreateCustomerRequest;
import com.ecommerce.Customer.dto.customerResponse;
import com.ecommerce.Customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CreateCustomerRequest request) {

        if(request == null) return null;

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public customerResponse fromCustomer(Customer customer){
        if(customer == null) return null;
        return new customerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
