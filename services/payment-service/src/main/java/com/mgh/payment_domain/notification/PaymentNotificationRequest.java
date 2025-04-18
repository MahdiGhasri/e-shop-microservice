package com.mgh.payment_domain.notification;

import java.math.BigDecimal;

import com.mgh.payment_domain.payment.dto.PaymentMethod;

public record PaymentNotificationRequest(String orderReference, BigDecimal amount, PaymentMethod paymentMethod,
		String customerFirstname, String customerLastname, String customerEmail) {

}
