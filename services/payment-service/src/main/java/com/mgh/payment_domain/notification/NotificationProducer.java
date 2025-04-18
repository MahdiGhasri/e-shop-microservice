package com.mgh.payment_domain.notification;

public interface NotificationProducer {

	void sendNotification(PaymentNotificationRequest request);
}