package com.mgh.payment_domain.payment.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgh.payment_domain.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}