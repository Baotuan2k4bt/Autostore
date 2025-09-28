package com.example.autostore.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class BookingPointDTO {
    private String day;    // "2025-09-22"
    private Long bookings;

    public BookingPointDTO(String day, Long bookings) {
        this.day = day;
        this.bookings = bookings;
    }
    public BookingPointDTO() {}
    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }
    public Long getBookings() { return bookings; }
    public void setBookings(Long bookings) { this.bookings = bookings; }
}