package com.mgh.payment_domain.payment.service.impl;

import org.springframework.stereotype.Service;

import com.mgh.payment_domain.payment.dto.PaymentRequest;
import com.mgh.payment_domain.payment.entity.Payment;
import com.mgh.payment_domain.payment.service.PaymentMapper;

@Service
class PaymentMapperImpl implements PaymentMapper {

	@Override
	public Payment toPayment(PaymentRequest request) {
		if (request == null) {
			return null;
		}
		return Payment.builder().id(request.id()).paymentMethod(request.paymentMethod()).amount(request.amount())
				.orderId(request.orderId()).build();
	}
}
