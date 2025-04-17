package com.mgh.order.orderitem;

import org.springframework.stereotype.Service;

import com.mgh.order.orderdomain.Order;

@Service
class OrderItemMapperImpl implements OrderItemMapper {

	@Override
	public OrderItem toOrderItem(OrderItemRequest request) {
		return OrderItem.builder().id(request.orderId()).productId(request.productId())
				.order(Order.builder().id(request.orderId()).build()).quantity(request.quantity()).build();
	}

	@Override
	public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
		return new OrderItemResponse(orderItem.getId(), orderItem.getQuantity());
	}

}
