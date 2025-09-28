package com.example.autostore.mapper;

import com.example.autostore.dto.admin.PaymentDTO;
import com.example.autostore.model.Booking;
import com.example.autostore.model.Payment;

public class PaymentMapper {

    // Entity -> DTO
    public static PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getPaymentId(),
                payment.getBooking().getBookingId(),
                payment.getBooking().getCustomer().getCustomerName(),
                payment.getBooking().getCar().getCarName(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaymentDate()
        );
    }

    // DTO -> Entity
    public static Payment toEntity(PaymentDTO dto, Booking booking) {
        return Payment.builder()
                .paymentId(dto.getPaymentId())
                .booking(booking) // cần truyền booking từ service
                .amount(dto.getAmount())
                .paymentMethod(dto.getMethod())
                .status(dto.getStatus())
                .paymentDate(dto.getPaymentDate())
                .build();
    }
}
