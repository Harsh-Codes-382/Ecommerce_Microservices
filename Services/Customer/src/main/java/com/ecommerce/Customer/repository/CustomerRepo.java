package com.ecommerce.Customer.repository;

import com.ecommerce.Customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {

}
