package com.mgh.customer.dto;

import com.mgh.customer.entity.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
		String id, 
		@NotNull(message = "Customer firstname is required") 
		String firstname,
		@NotNull(message = "Customer firstname is required") 
		String lastname,
		@NotNull(message = "Customer Email is required") 
		String email,
		@Email(message = "Customer Email is not a valid email address")
		Address address) {}
