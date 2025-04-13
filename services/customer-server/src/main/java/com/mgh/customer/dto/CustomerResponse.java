package com.mgh.customer.dto;

import com.mgh.customer.entity.Address;

public record CustomerResponse(
		String id,
	    String firstname,
	    String lastname,
	    String email,
	    Address address) {}
