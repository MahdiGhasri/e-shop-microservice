package com.mgh.payment_domain.payment.service.impl;

import org.springframework.stereotype.Service;

import com.mgh.payment_domain.notification.NotificationProducer;
import com.mgh.payment_domain.notification.PaymentNotificationRequest;
import com.mgh.payment_domain.payment.dto.PaymentRequest;
import com.mgh.payment_domain.payment.service.PaymentMapper;
import com.mgh.payment_domain.payment.service.PaymentRepository;
import com.mgh.payment_domain.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository repository;
	private final PaymentMapper mapper;
	private final NotificationProducer notificationProducer;

	@Override
	public Integer createPayment(PaymentRequest request) {
		var payment = this.repository.save(this.mapper.toPayment(request));

		this.notificationProducer.sendNotification(
				new PaymentNotificationRequest(request.orderReference(), request.amount(), request.paymentMethod(),
						request.customer().firstname(), request.customer().lastname(), request.customer().email()));
		return payment.getId();

	}

}
