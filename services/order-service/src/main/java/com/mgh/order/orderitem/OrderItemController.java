package com.mgh.order.orderitem;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-items")
@RequiredArgsConstructor
public class OrderItemController {
	private final OrderItemService service;

	@GetMapping("/order/{order-id}")
	public ResponseEntity<List<OrderItemResponse>> findByOrderId(@PathVariable("order-id") Integer orderId) {
		return ResponseEntity.ok(service.findAllByOrderId(orderId));
	}
}
