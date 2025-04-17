package com.mgh.order.payment;

import java.math.BigDecimal;

import com.mgh.order.customer.CustomerResponse;
import com.mgh.order.orderdomain.PaymentMethod;

public record PaymentRequest(BigDecimal amount, PaymentMethod paymentMethod, Integer orderId, String orderReference,
		CustomerResponse customer) {
}