package com.example.autostore.repository;

import com.example.autostore.Enum.BookingStatus;
import com.example.autostore.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Integer> {
    // Lọc theo trạng thái (PENDING, CONFIRMED, ...)
    List<Booking> findByStatus(BookingStatus status);

    // Lọc theo ID khách hàng
    List<Booking> findByCustomer_CustomerId(Integer customerId);

    // Lọc theo ID xe
    List<Booking> findByCar_CarId(Integer carId);

    // Lọc theo trạng thái + khách hàng (ví dụ: tất cả booking PENDING của 1 khách)
    List<Booking> findByCustomer_CustomerIdAndStatus(Integer customerId, BookingStatus status);

    Page<Booking> findByCustomer_CustomerNameContainingIgnoreCaseOrCar_CarNameContainingIgnoreCase(
            String customerName, String carName, Pageable pageable
    );
    @Query(value = """
        WITH RECURSIVE date_series AS (
            SELECT :startDate AS dayKey
            UNION ALL
            SELECT DATE_ADD(dayKey, INTERVAL 1 DAY)
            FROM date_series
            WHERE dayKey < :endDate
        )
        SELECT ds.dayKey,
               COALESCE(COUNT(b.bookingId), 0) AS bookingCount
        FROM date_series ds
        LEFT JOIN Booking b
               ON DATE(b.createdAt) = ds.dayKey
              AND b.status = 'CONFIRMED'
        LEFT JOIN Car c ON b.carId = c.carId
        LEFT JOIN Brand br ON c.brandId = br.brandId
        LEFT JOIN CarType ct ON c.carTypeId = ct.carTypeId
        WHERE (:Brand IS NULL OR LOWER(br.brandName) = LOWER(:brand))
          AND (:CarType IS NULL OR LOWER(ct.typeName) = LOWER(:carType))
        GROUP BY ds.dayKey
        ORDER BY ds.dayKey
        """, nativeQuery = true)
    List<Object[]> getDailyBookingsWithZeros(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("brand") String brand,
            @Param("carType") String carType
    );


}