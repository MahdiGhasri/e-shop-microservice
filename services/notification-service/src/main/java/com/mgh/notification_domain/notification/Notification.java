package com.mgh.notification_domain.notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mgh.notification_domain.kafka.order.OrderConfirmation;
import com.mgh.notification_domain.kafka.payment.PaymentConfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}