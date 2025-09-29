package com.ecommerce.Customer.controller;

import com.ecommerce.Customer.DTO.CreateCustomerRequest;
import com.ecommerce.Customer.DTO.customerResponse;
import com.ecommerce.Customer.model.Customer;
import com.ecommerce.Customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CreateCustomerRequest request
    ){
        return ResponseEntity.ok(customerService.create(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CreateCustomerRequest req
    ){
        customerService.updateCustomer(req);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<customerResponse>> getCustomers(){
        List<customerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/find/{customerId}")
    public ResponseEntity<customerResponse> findCustomer(@PathVariable("customerId") String customerId){
        customerResponse foundCustomer = customerService.findCustomer(customerId);
        return ResponseEntity.ok(foundCustomer);
    }

    @GetMapping("/exist/{customerId}")
    public ResponseEntity<Boolean> isCustomerExist(@PathVariable("customerId") String customerId){
        boolean foundCustomer = customerService.checkCustomerExist(customerId);
        return ResponseEntity.ok(foundCustomer);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }


}
