package com.mgh.order.orderdomain;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mgh.order.customer.CustomerClient;
import com.mgh.order.exception.BusinessException;
import com.mgh.order.kafka.OrderConfirmation;
import com.mgh.order.kafka.OrderProducer;
import com.mgh.order.orderitem.OrderItemRequest;
import com.mgh.order.orderitem.OrderItemService;
import com.mgh.order.payment.PaymentClient;
import com.mgh.order.payment.PaymentRequest;
import com.mgh.order.product.ProductClient;
import com.mgh.order.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;
	private final OrderMapper mapper;
	private final CustomerClient customerClient;
	private final PaymentClient paymentClient;
	private final ProductClient productClient;
	private final OrderItemService orderItemService;
	private final OrderProducer orderProducer;

	@Override
	@Transactional
	public Integer createOrder(OrderRequest request) {
		var customer = this.customerClient.findCustomerById(request.customerId()).orElseThrow(
				() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

		var purchasedProducts = productClient.purchaseProducts(request.products());
		var orderToSave = mapper.toOrder(request);
		var order = this.repository.save(orderToSave);

		for (PurchaseRequest purchaseRequest : request.products()) {
			orderItemService.saveOrderItem(
					new OrderItemRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
		}
		var paymentRequest = new PaymentRequest(request.amount(), request.paymentMethod(), order.getId(),
				order.getReference(), customer);
		paymentClient.requestOrderPayment(paymentRequest);

		orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(),
				request.paymentMethod(), customer, purchasedProducts));

		return order.getId();
	}

	@Override
	public List<OrderResponse> findAllOrders() {
		return this.repository.findAll().stream().map(this.mapper::fromOrder).toList();
	}

	@Override
	public OrderResponse findById(Integer id) {
		return this.repository.findById(id).map(this.mapper::fromOrder).orElseThrow(
				() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
	}
}
