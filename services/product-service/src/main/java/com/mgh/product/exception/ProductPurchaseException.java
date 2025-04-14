package com.mgh.product.exception;

public class ProductPurchaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductPurchaseException(String s) {
        super(s);
    }
}