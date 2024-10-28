package com.project.garbagecollectionsys.payment;

import com.project.garbagecollectionsys.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByUserId(Long userId);
    List<Payment> findByMethod(String method);
}

