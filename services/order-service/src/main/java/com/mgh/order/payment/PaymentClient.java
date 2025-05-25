package com.mgh.order.payment;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface PaymentClient {

  @PostExchange
  Integer requestOrderPayment(@RequestBody PaymentRequest request);
}
