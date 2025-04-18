package com.mgh.payment_domain.payment.service;

import com.mgh.payment_domain.payment.dto.PaymentRequest;

public interface PaymentService {
	Integer createPayment(PaymentRequest request);
}
