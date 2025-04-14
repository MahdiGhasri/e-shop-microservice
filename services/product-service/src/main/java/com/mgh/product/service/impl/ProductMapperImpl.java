package com.mgh.product.service.impl;

import org.springframework.stereotype.Component;

import com.mgh.product.dto.ProductPurchaseResponse;
import com.mgh.product.dto.ProductRequest;
import com.mgh.product.dto.ProductResponse;
import com.mgh.product.entity.Category;
import com.mgh.product.entity.Product;
import com.mgh.product.service.ProductMapper;

@Component
class ProductMapperImpl implements ProductMapper {

	@Override
	public Product toProduct(ProductRequest request) {
		var category = Category.builder()
				.id(request.categoryId())
				.build();
		
		return Product.builder()
				.id(request.id())
				.name(request.name())
				.description(request.description())
				.availableQuantity(request.availableQuantity())
				.price(request.price())
				.category(category)
				.build();
	}

	@Override
	public ProductResponse toProductResponse(Product product) {
		return new ProductResponse(
				product.getId(), 
				product.getName(), 
				product.getDescription(),
				product.getAvailableQuantity(), 
				product.getPrice(), 
				product.getCategory().getId(),
				product.getCategory().getName(), 
				product.getCategory().getDescription());
	}

	@Override
	public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
		return new ProductPurchaseResponse(
				product.getId(), 
				product.getName(), 
				product.getDescription(),
				product.getPrice(), 
				quantity);
	}
}
