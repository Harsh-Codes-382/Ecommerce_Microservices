package com.ecommerce.Customer.service;

import com.ecommerce.Customer.dto.CreateCustomerRequest;
import com.ecommerce.Customer.dto.customerResponse;
import com.ecommerce.Customer.Exception.CustomerNotFoundException;
import com.ecommerce.Customer.mapper.CustomerMapper;
import com.ecommerce.Customer.model.Customer;
import com.ecommerce.Customer.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public String create(CreateCustomerRequest request) {
        var customer = customerRepo.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    //    @Transactional
    public void updateCustomer(CreateCustomerRequest req) {
        var customer = customerRepo.findById(req.id())
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                format("cannot update Customer:: Customer not found with this id=%s", req.id())
                        )
                );

        mergeCustomer(customer, req);
        customerRepo.save(customer);
    }

    private void mergeCustomer(Customer customer, CreateCustomerRequest req) {
        if (StringUtils.isNotBlank(req.firstname())) {
            customer.setFirstname(req.firstname());
        }
        if (StringUtils.isNotBlank(req.email())) {
            customer.setEmail(req.email());
        }
        if (StringUtils.isNotBlank(req.lastname())) {
            customer.setLastname(req.lastname());
        }
        if (req.address() != null) {
            customer.setAddress(req.address());
        }
    }

    public List<customerResponse> getAllCustomers() {

        return customerRepo.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public customerResponse findCustomer(String customerId) throws CustomerNotFoundException {
        return customerRepo.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() ->
                new CustomerNotFoundException(
                        format("Customer Not Found:: Provided Id=%s not belong to any Customer", customerId)
                )
        );
    }

    public boolean checkCustomerExist(String customerId) {
        return customerRepo.findById(customerId)
                .isPresent();
    }

    public void deleteCustomer(String customerId) {
        customerRepo.deleteById(customerId);
    }
}
