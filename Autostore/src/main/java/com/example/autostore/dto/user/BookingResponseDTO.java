package com.example.autostore.dto.user;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Integer bookingId;
    private String status; // PENDING, CONFIRMED, CANCELED...

    // Xe
    private Integer carId;
    private String carName;
    private String carImage;

    // Thông tin thuê
    private String pickupLocation;
    private String returnLocation;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private LocalTime pickupTime;
    private LocalTime returnTime;
    private Double totalAmount;
    private Double depositAmount;

    // Khách hàng
    private String customerName;
    private String customerPhone;
    private String customerEmail;
}
