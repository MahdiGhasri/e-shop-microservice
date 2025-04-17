package com.mgh.order.orderitem;

public interface OrderItemMapper {
	OrderItem toOrderItem(OrderItemRequest request);

	OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
