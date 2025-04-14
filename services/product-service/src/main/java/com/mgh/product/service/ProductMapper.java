package com.mgh.product.service;

import com.mgh.product.dto.ProductPurchaseResponse;
import com.mgh.product.dto.ProductRequest;
import com.mgh.product.dto.ProductResponse;
import com.mgh.product.entity.Product;

public interface ProductMapper {
	
	Product toProduct(ProductRequest request);

	ProductResponse toProductResponse(Product product);

	ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity);
}
