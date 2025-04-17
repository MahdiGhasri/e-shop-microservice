package com.mgh.order.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.mgh.order.customer.CustomerResponse;
import com.mgh.order.orderdomain.PaymentMethod;
import com.mgh.order.product.PurchaseResponse;

public record OrderConfirmation(String orderReference, BigDecimal totalAmount, PaymentMethod paymentMethod,
		CustomerResponse customer, List<PurchaseResponse> products

) {
}