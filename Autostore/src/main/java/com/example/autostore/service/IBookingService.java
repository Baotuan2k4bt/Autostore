package com.example.autostore.service;

import com.example.autostore.Enum.BookingStatus;
import com.example.autostore.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Integer id);
    Booking updateBookingStatus(Integer id, BookingStatus status);
    void deleteBooking(Integer id);
    Page<Booking> getBookings(String keyword, Pageable pageable);
}
