package com.mgh.order.orderdomain;

public interface OrderMapper {
	Order toOrder(OrderRequest request);
	OrderResponse fromOrder(Order order);
}
