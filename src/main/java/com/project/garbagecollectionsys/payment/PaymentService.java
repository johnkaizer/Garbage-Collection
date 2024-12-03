package com.project.garbagecollectionsys.payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment, String subscriptionType) {
        // Set payment date and calculate expiry date
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);

        switch (subscriptionType.toUpperCase()) {
            case "WEEKLY":
                payment.setExpiryDate(now.plusDays(7));
                payment.setAmount(BigDecimal.valueOf(200));
                break;
            case "MONTHLY":
                payment.setExpiryDate(now.plusDays(30));
                payment.setAmount(BigDecimal.valueOf(500));
                break;
            case "YEARLY":
                payment.setExpiryDate(now.plusDays(360));
                payment.setAmount(BigDecimal.valueOf(4000));
                break;
            default:
                throw new IllegalArgumentException("Invalid subscription type");
        }

        // Save the payment
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }
}
