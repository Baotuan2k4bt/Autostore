package com.example.autostore.repository;

import com.example.autostore.Enum.BookingStatus;
import com.example.autostore.Enum.PaymentStatus;
import com.example.autostore.model.Booking;
import com.example.autostore.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("""
        SELECT p FROM Payment p
        WHERE (:keyword IS NULL OR 
               LOWER(p.booking.customer.customerName) LIKE LOWER(CONCAT('%', :keyword, '%')) 
               OR LOWER(p.booking.car.carName) LIKE LOWER(CONCAT('%', :keyword, '%')))
          AND (:status IS NULL OR p.status = :status)
          AND (:customerId IS NULL OR p.booking.customer.customerId = :customerId)
        """)
    Page<Payment> searchPayments(
            @Param("keyword") String keyword,
            @Param("status") PaymentStatus status,
            @Param("customerId") Integer customerId,
            Pageable pageable
    );

    @Query(value = """
        SELECT DATE_FORMAT(p.paymentDate, '%Y-%m') AS monthKey,
               COALESCE(SUM(p.amount), 0) AS revenue
        FROM Payment p
        JOIN Booking b ON b.bookingId = p.bookingId
        JOIN Car c ON c.carId = b.carId
        JOIN Brand br ON br.brandId = c.brandId
        JOIN CarType ct ON ct.carTypeId = c.carTypeId
        WHERE p.status = 'SUCCESS'
          AND b.status = 'CONFIRMED'
          AND p.paymentDate BETWEEN :startDate AND :endDate
          AND (:brand IS NULL OR LOWER(br.brandName) = LOWER(:brand))
          AND (:carType IS NULL OR LOWER(ct.typeName) = LOWER(:carType))
        GROUP BY monthKey
        ORDER BY monthKey
        """, nativeQuery = true)
    List<Object[]> getMonthlyRevenue(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("brand") String brand,
            @Param("carType") String carType
    );

    }



