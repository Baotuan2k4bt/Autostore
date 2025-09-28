package com.example.autostore.model;

import com.example.autostore.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking;  // Liên kết với đơn đặt xe

    @Column(nullable = false)
    private Double amount; // Số tiền thanh toán

    @Column(nullable = false, length = 50)
    private String paymentMethod; // CreditCard, MoMo, VNPay...

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // SUCCESS, FAILED, PENDING

    @Column(updatable = false)
    private LocalDateTime paymentDate;



    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = PaymentStatus.PENDING;
        }
    }
}
