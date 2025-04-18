package com.mgh.payment_domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String msg;
}