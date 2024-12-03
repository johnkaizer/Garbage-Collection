package com.project.garbagecollectionsys.payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setPhone(paymentRequest.getPhone());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setUserId(paymentRequest.getUserId());

        Payment savedPayment = paymentService.createPayment(payment, paymentRequest.getSubscriptionType());
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments(@RequestParam Long userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return ResponseEntity.ok(payments);
    }

}

