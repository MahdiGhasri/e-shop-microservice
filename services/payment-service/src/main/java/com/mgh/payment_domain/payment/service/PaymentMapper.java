package com.mgh.payment_domain.payment.service;

import com.mgh.payment_domain.payment.dto.PaymentRequest;
import com.mgh.payment_domain.payment.entity.Payment;

public interface PaymentMapper {
	Payment toPayment(PaymentRequest request);
}
