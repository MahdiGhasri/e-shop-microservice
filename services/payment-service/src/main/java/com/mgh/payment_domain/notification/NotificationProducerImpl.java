package com.mgh.payment_domain.notification;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class NotificationProducerImpl implements NotificationProducer {
	private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

	@Override
	public void sendNotification(PaymentNotificationRequest request) {
		log.info("Sending notification with body = < {} >", request);
		Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request)
				.setHeader(TOPIC, "payment-topic").build();

		kafkaTemplate.send(message);

	}
}
