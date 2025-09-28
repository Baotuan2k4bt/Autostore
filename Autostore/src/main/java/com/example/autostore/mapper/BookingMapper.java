package com.example.autostore.mapper;


import com.example.autostore.dto.admin.BookingDTO;
import com.example.autostore.model.Booking;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) return null;

        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setPickupLocation(booking.getPickupLocation());
        dto.setReturnLocation(booking.getReturnLocation());
        dto.setPickupDate(booking.getPickupDate() != null ? booking.getPickupDate().toString() : null);
        dto.setReturnDate(booking.getReturnDate() != null ? booking.getReturnDate().toString() : null);
        dto.setPickupTime(booking.getPickupTime() != null ? booking.getPickupTime().toString() : null);
        dto.setReturnTime(booking.getReturnTime() != null ? booking.getReturnTime().toString() : null);
        dto.setNotes(booking.getNotes());
        dto.setTotalAmount(booking.getTotalAmount());
        dto.setDepositAmount(booking.getDepositAmount());
        dto.setStatus(booking.getStatus() != null ? booking.getStatus().name() : null);

        if (booking.getCustomer() != null) {
            dto.setCustomerName(booking.getCustomer().getCustomerName());
            dto.setCustomerPhone(booking.getCustomer().getCustomerPhone());
        }

        if (booking.getCar() != null) {
            dto.setCarName(booking.getCar().getCarName());
            if (booking.getCar().getBrand() != null) {
                dto.setBrandName(booking.getCar().getBrand().getBrandName());
            }
        }

        return dto;
    }
}
