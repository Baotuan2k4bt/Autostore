package com.example.autostore.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer bookingId;

    // Thông tin thuê xe
    private String pickupLocation;
    private String returnLocation;
    private String pickupDate;
    private String returnDate;
    private String pickupTime;
    private String returnTime;
    private String notes;

    // Tài chính
    private Double totalAmount;
    private Double depositAmount;
    private String status;

    // Khách hàng
    private String customerName;
    private String customerPhone;

    // Xe
    private String carName;
    private String brandName;
}
