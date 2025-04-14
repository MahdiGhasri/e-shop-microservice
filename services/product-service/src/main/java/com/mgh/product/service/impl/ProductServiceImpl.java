package com.mgh.product.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mgh.product.dto.ProductPurchaseRequest;
import com.mgh.product.dto.ProductPurchaseResponse;
import com.mgh.product.dto.ProductRequest;
import com.mgh.product.dto.ProductResponse;
import com.mgh.product.exception.ProductPurchaseException;
import com.mgh.product.service.ProductMapper;
import com.mgh.product.service.ProductRepository;
import com.mgh.product.service.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final ProductMapper mapper;

	@Override
	public Integer createProduct(ProductRequest request) {
		var product = mapper.toProduct(request);
		return repository.save(product).getId();
	}

	@Override
	public ProductResponse findById(Integer id) {
		return repository.findById(id).map(mapper::toProductResponse)
				.orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: " + id));
	}

	@Override
	public List<ProductResponse> findAll() {
		return repository.findAll().stream().map(mapper::toProductResponse).toList();
	}

	@Override
	@Transactional(rollbackFor = ProductPurchaseException.class)
	public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
		var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
		var storedProducts = repository.findAllByIdInOrderById(productIds);
		
		if (productIds.size() != storedProducts.size()) {
			throw new ProductPurchaseException("One or more products does not exist");
		}
		
		var sortedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
		var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
		
		for (int i = 0; i < storedProducts.size(); i++) {
			var product = storedProducts.get(i);
			var productRequest = sortedRequest.get(i);
			if (product.getAvailableQuantity() < productRequest.quantity()) {
				throw new ProductPurchaseException(
						"Insufficient stock quantity for product with ID:: " + productRequest.productId());
			}
			var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
			product.setAvailableQuantity(newAvailableQuantity);
			repository.save(product);
			purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
		}
		return purchasedProducts;
	}

}
