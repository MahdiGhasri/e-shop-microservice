package com.mgh.customer.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mgh.customer.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
