package com.example.autostore.model;

import com.example.autostore.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    // Thông tin thuê xe
    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String returnLocation;

    @Column(nullable = false)
    private LocalDate pickupDate;

    @Column(nullable = false)
    private LocalDate returnDate;

    @Column(nullable = false)
    private LocalTime pickupTime;

    @Column(nullable = false)
    private LocalTime returnTime;

    @Column(length = 500)
    private String notes;

    // Tài chính
    private Double totalAmount;
    private Double depositAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED, CANCELED, COMPLETED

    private Double paidAmount = 0.0;

    // Quan hệ với Customer
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    // Quan hệ với Car
    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;


    // Thời gian tạo & cập nhật
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = BookingStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
