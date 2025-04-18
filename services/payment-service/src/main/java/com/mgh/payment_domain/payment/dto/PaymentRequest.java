package com.mgh.payment_domain.payment.dto;

import java.math.BigDecimal;

import com.mgh.payment_domain.customer.Customer;

public record PaymentRequest(Integer id, BigDecimal amount, PaymentMethod paymentMethod, Integer orderId,
		String orderReference, Customer customer) {
}