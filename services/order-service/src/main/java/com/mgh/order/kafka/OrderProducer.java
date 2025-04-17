package com.mgh.order.kafka;

public interface OrderProducer {
	void sendOrderConfirmation(OrderConfirmation orderConfirmation);
}
