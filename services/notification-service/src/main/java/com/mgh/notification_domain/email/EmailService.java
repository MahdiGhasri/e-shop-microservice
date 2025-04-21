package com.mgh.notification_domain.email;

import java.math.BigDecimal;
import java.util.List;

import com.mgh.notification_domain.kafka.order.Product;

import jakarta.mail.MessagingException;

public interface EmailService {
	void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount, String orderReference)
			throws MessagingException;

	void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount,
			String orderReference, List<Product> products) throws MessagingException;
}
