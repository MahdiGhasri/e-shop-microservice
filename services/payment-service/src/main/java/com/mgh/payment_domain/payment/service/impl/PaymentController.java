package com.mgh.payment_domain.payment.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgh.payment_domain.payment.dto.PaymentRequest;
import com.mgh.payment_domain.payment.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService service;

	@PostMapping
	public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request) {
		return ResponseEntity.ok(this.service.createPayment(request));
	}
}