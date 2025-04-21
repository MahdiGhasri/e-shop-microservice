package com.mgh.notification_domain.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.mgh.notification_domain.kafka.payment.PaymentMethod;

public record OrderConfirmation(String orderReference, BigDecimal totalAmount, PaymentMethod paymentMethod,
		Customer customer, List<Product> products

) {
}