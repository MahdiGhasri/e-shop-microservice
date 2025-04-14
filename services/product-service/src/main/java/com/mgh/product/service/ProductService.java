package com.mgh.product.service;

import java.util.List;

import com.mgh.product.dto.ProductPurchaseRequest;
import com.mgh.product.dto.ProductPurchaseResponse;
import com.mgh.product.dto.ProductRequest;
import com.mgh.product.dto.ProductResponse;

public interface ProductService {
	
	Integer createProduct(ProductRequest request);

	ProductResponse findById(Integer id);

	List<ProductResponse> findAll();

	List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);
}
