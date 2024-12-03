package com.project.garbagecollectionsys.payment;

import com.project.garbagecollectionsys.enums.PaymentStatus;
import com.project.garbagecollectionsys.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    private Long userId; //get the id of the current logged in user
    private BigDecimal amount; // SUBSCRIPTION DROP DOWN MENU
    private LocalDateTime paymentDate;// now
    private LocalDateTime expiryDate; // AS PER THE SELECTED SUBSCRIPTION day after payment day
    private String paymentMethod;   //DROP DOWN MENU WITH AND CASH MPESA AND CARD COMING SOON
}
