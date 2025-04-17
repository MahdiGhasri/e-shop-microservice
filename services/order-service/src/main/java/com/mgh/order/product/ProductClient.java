package com.mgh.order.product;

import java.util.List;

public interface ProductClient {
	List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody);
}
