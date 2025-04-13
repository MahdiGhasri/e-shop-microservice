package com.mgh.customer.service;

import java.util.List;

import com.mgh.customer.dto.CustomerRequest;
import com.mgh.customer.dto.CustomerResponse;

public interface CustomerService {
	
	String createCustomer(CustomerRequest request);

	void updateCustomer(CustomerRequest request);

	List<CustomerResponse> findAllCustomers();

	CustomerResponse findById(String id);

	boolean existsById(String id);

	void deleteCustomer(String id);
}
