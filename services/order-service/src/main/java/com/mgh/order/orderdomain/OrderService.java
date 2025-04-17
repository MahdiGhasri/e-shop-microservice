package com.mgh.order.orderdomain;

import java.util.List;

public interface OrderService {
	Integer createOrder(OrderRequest request);
	List<OrderResponse> findAllOrders();
	OrderResponse findById(Integer id);
}
