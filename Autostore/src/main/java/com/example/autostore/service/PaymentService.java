package com.example.autostore.service;

import com.example.autostore.Enum.PaymentStatus;
import com.example.autostore.model.Booking;
import com.example.autostore.model.Payment;
import com.example.autostore.repository.IBookingRepository;
import com.example.autostore.repository.IPaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentService implements IPaymentService {
    private final IPaymentRepository paymentRepository;
    private final IBookingRepository bookingRepository;

    public PaymentService(IPaymentRepository paymentRepository, IBookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Page<Payment> getPayments(String keyword, PaymentStatus status, Integer customerId, Pageable pageable) {
        return paymentRepository.searchPayments((keyword == null || keyword.isBlank()) ? null : keyword, status, customerId, pageable);
    }

    @Override
    public Payment getPaymentById(Integer id) {

        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);

    }

    @Override
    public Payment updatePaymentStatus(Integer id, PaymentStatus status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);

        // Nếu hoàn tiền thì cập nhật Booking.paidAmount giảm xuống
        if (status == PaymentStatus.REFUNDED) {
            Booking booking = payment.getBooking();
            if (booking.getPaidAmount() != null) {
                booking.setPaidAmount(booking.getPaidAmount() - payment.getAmount());
            }
            bookingRepository.save(booking);
        }

        return paymentRepository.save(payment);
    }

}
