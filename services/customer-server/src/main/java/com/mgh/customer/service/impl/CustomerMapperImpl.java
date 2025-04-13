package com.mgh.customer.service.impl;

import com.mgh.customer.dto.CustomerRequest;
import com.mgh.customer.dto.CustomerResponse;
import com.mgh.customer.entity.Customer;
import com.mgh.customer.service.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
class CustomerMapperImpl implements CustomerMapper {
	@Override
	public Customer toCustomer(CustomerRequest request) {
		if (request == null) {
			return null;
		}
		return Customer.builder()
				.id(request.id())
				.firstname(request.firstname())
				.lastname(request.lastname())
				.email(request.email())
				.address(request.address()).build();
	}

	@Override
	public CustomerResponse fromCustomer(Customer customer) {
		if (customer == null) {
			return null;
		}
		return new CustomerResponse(
				customer.getId(), 
				customer.getFirstname(), 
				customer.getLastname(),
				customer.getEmail(), 
				customer.getAddress());
	}
}
