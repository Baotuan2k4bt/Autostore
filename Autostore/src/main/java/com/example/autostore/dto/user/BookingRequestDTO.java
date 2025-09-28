package com.example.autostore.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@lombok.Data
@Getter
@Setter

public class BookingRequestDTO {
    // Xe thuê
    private Integer carId;

    // Thông tin thuê
    private String pickupLocation;
    private String returnLocation;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private LocalTime pickupTime;
    private LocalTime returnTime;
    private String notes;

    // Thông tin khách hàng
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String idNumber;       // CCCD/CMND
    private String licenseNumber;  // GPLX

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Integer carId, String pickupLocation, String returnLocation, LocalDate pickupDate, LocalDate returnDate, LocalTime pickupTime, LocalTime returnTime, String notes, String fullName, String email, String phone, String address, String idNumber, String licenseNumber) {
        this.carId = carId;
        this.pickupLocation = pickupLocation;
        this.returnLocation = returnLocation;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.pickupTime = pickupTime;
        this.returnTime = returnTime;
        this.notes = notes;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.idNumber = idNumber;
        this.licenseNumber = licenseNumber;
    }


}
