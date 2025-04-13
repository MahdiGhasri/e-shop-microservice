package com.mgh.customer.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mgh.customer.dto.CustomerRequest;
import com.mgh.customer.dto.CustomerResponse;
import com.mgh.customer.entity.Customer;
import com.mgh.customer.exception.CustomerNotFoundException;
import com.mgh.customer.service.CustomerMapper;
import com.mgh.customer.service.CustomerRepository;
import com.mgh.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	@Override
	public String createCustomer(CustomerRequest request) {
		var customer = this.repository.save(mapper.toCustomer(request));
		return customer.getId();
	}

	@Override
	public void updateCustomer(CustomerRequest request) {
		var customer = this.repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
				String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())));
		mergeCustomer(customer, request);
		this.repository.save(customer);
	}

	@Override
	public List<CustomerResponse> findAllCustomers() {
		return this.repository.findAll().stream().map(this.mapper::fromCustomer).toList();
	}

	@Override
	public CustomerResponse findById(String id) {
		return this.repository.findById(id).map(mapper::fromCustomer).orElseThrow(
				() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
	}

	@Override
	public boolean existsById(String id) {
		return this.repository.existsById(id);
	}

	@Override
	public void deleteCustomer(String id) {
		this.repository.deleteById(id);
	}

	private void mergeCustomer(Customer customer, CustomerRequest request) {
		if (StringUtils.isNotBlank(request.firstname())) {
			customer.setFirstname(request.firstname());
		}
		if (StringUtils.isNotBlank(request.lastname())) {
			customer.setLastname(request.lastname());
		}
		if (StringUtils.isNotBlank(request.email())) {
			customer.setEmail(request.email());
		}
		if (request.address() != null) {
			customer.setAddress(request.address());
		}
	}
}
