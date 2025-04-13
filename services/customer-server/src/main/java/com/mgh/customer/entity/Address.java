package com.mgh.customer.entity;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private String street;
	private String houseNumber;
	private String zipCode;
}
