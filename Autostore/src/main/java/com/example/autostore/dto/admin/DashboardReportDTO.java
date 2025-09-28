package com.example.autostore.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class DashboardReportDTO {
    private List<RevenuePointDTO> monthlyRevenue;
    private List<BrandRatioDTO> brandRatio;
    private List<BookingPointDTO> dailyBookings;

    public DashboardReportDTO(List<RevenuePointDTO> monthlyRevenue, List<BrandRatioDTO> brandRatio, List<BookingPointDTO> dailyBookings) {
        this.monthlyRevenue = monthlyRevenue;
        this.brandRatio = brandRatio;
        this.dailyBookings = dailyBookings;
    }
    public DashboardReportDTO() {}

    public List<RevenuePointDTO> getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setMonthlyRevenue(List<RevenuePointDTO> monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }

    public List<BrandRatioDTO> getBrandRatio() {
        return brandRatio;
    }

    public void setBrandRatio(List<BrandRatioDTO> brandRatio) {
        this.brandRatio = brandRatio;
    }

    public List<BookingPointDTO> getDailyBookings() {
        return dailyBookings;
    }

    public void setDailyBookings(List<BookingPointDTO> dailyBookings) {
        this.dailyBookings = dailyBookings;
    }
}