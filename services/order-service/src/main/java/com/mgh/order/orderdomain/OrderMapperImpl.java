package com.mgh.order.orderdomain;

import org.springframework.stereotype.Service;

@Service
class OrderMapperImpl implements OrderMapper {

	@Override
	public Order toOrder(OrderRequest request) {
		if (request == null) {
			return null;
		}
		return Order.builder().id(request.id()).reference(request.reference()).paymentMethod(request.paymentMethod())
				.customerId(request.customerId()).build();
	}

	@Override
	public OrderResponse fromOrder(Order order) {
		return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(), order.getPaymentMethod(),
				order.getCustomerId());
	}

}