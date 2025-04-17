package com.mgh.order.orderitem;

import java.util.List;

public interface OrderItemService {
	Integer saveOrderItem(OrderItemRequest request);

	List<OrderItemResponse> findAllByOrderId(Integer orderId);
}
