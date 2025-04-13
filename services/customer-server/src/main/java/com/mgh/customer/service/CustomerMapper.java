package com.mgh.customer.service;

import com.mgh.customer.dto.CustomerRequest;
import com.mgh.customer.dto.CustomerResponse;
import com.mgh.customer.entity.Customer;

public interface CustomerMapper {
	Customer toCustomer(CustomerRequest request);

	CustomerResponse fromCustomer(Customer customer);
}
