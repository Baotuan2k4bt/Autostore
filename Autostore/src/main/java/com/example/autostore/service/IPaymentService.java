package com.example.autostore.service;



import com.example.autostore.Enum.PaymentStatus;
import com.example.autostore.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPaymentService {
    Page<Payment> getPayments(String keyword, PaymentStatus status, Integer customerId, Pageable pageable);
    Payment getPaymentById(Integer id);
    Payment savePayment(Payment payment);
    void deletePayment(Integer id);
    Payment updatePaymentStatus(Integer id, PaymentStatus status);


}

