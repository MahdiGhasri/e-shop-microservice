package com.mgh.order.orderitem;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class OrderItemServiceImpl implements OrderItemService {

	private final OrderItemRepository repository;
	private final OrderItemMapper mapper;

	@Override
	public Integer saveOrderItem(OrderItemRequest request) {
		var order = mapper.toOrderItem(request);
		return repository.save(order).getId();
	}

	@Override
	public List<OrderItemResponse> findAllByOrderId(Integer orderId) {
		return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderItemResponse).toList();
	}

}
