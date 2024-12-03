package com.project.garbagecollectionsys.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String phone;
    private BigDecimal amount;
    private String paymentMethod;
    private String subscriptionType;
    private Long userId;
}
