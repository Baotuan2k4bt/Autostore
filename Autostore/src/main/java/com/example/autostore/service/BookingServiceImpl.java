package com.example.autostore.service;

import com.example.autostore.model.Booking;
import com.example.autostore.Enum.BookingStatus;
import com.example.autostore.repository.IBookingRepository;
import com.example.autostore.service.IBookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;

    public BookingServiceImpl(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public Booking updateBookingStatus(Integer id, BookingStatus status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Page<Booking> getBookings(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return bookingRepository.findByCustomer_CustomerNameContainingIgnoreCaseOrCar_CarNameContainingIgnoreCase(
                    keyword, keyword, pageable
            );
        }
        return bookingRepository.findAll(pageable);
    }

}
