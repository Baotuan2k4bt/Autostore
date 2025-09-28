package com.example.autostore.dto.admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FleetOverviewDTO {
    private long totalCars;       // Tổng số xe
    private long rentedCars;      // Xe đang thuê
    private long maintenanceCars; // Xe đang bảo dưỡng
    private double utilization;   // Tỷ lệ sử dụng (%)
}