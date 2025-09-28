package com.example.autostore.service;

import com.example.autostore.dto.admin.BookingPointDTO;
import com.example.autostore.dto.admin.BrandRatioDTO;
import com.example.autostore.dto.admin.DashboardReportDTO;
import com.example.autostore.dto.admin.RevenuePointDTO;
import com.example.autostore.repository.IBookingRepository;
import com.example.autostore.repository.ICarRepository;
import com.example.autostore.repository.IPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DashboardService implements IDashboardService {

    private final IPaymentRepository paymentRepository;
    private final ICarRepository carRepository;
    private final IBookingRepository bookingRepository;

    public DashboardService(IPaymentRepository paymentRepository,
                            ICarRepository carRepository,
                            IBookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public DashboardReportDTO getDashboardReport(LocalDate startDate,
                                                 LocalDate endDate,
                                                 String brand,
                                                 String carType) {

        if (startDate == null) startDate = LocalDate.now().minusDays(30);
        if (endDate == null) endDate = LocalDate.now();

        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        // Formatters
        DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMM yyyy");
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("dd/MM");

        // Doanh thu theo tháng
        List<RevenuePointDTO> monthlyRevenue = paymentRepository.getMonthlyRevenue(start, end, brand, carType)
                .stream()
                .map(r -> new RevenuePointDTO(
                        YearMonth.parse((String) r[0], DateTimeFormatter.ofPattern("yyyy-MM"))
                                .format(monthFmt),
                        ((Number) r[1]).doubleValue()
                ))
                .toList();

        // Brand ratio
        List<BrandRatioDTO> brandRatio = carRepository.getBrandRatio(carType)
                .stream()
                .map(r -> new BrandRatioDTO(
                        ((String) r[0]).toUpperCase(),
                        ((Number) r[1]).longValue()
                ))
                .toList();

        // Daily bookings (fill ngày trống = 0)
        List<BookingPointDTO> dailyBookings = bookingRepository.getDailyBookingsWithZeros(start, end, brand, carType)
                .stream()
                .map(r -> new BookingPointDTO(
                        ((java.sql.Date) r[0]).toLocalDate().format(dayFmt),
                        ((Number) r[1]).longValue()
                ))
                .toList();

        return new DashboardReportDTO(monthlyRevenue, brandRatio, dailyBookings);
    }
}
