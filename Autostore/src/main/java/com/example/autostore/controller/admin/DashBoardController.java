package com.example.autostore.controller.admin;

import com.example.autostore.dto.admin.DashboardReportDTO;
import com.example.autostore.service.IDashboardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "http://localhost:3000") // FE React/Next.js
public class DashBoardController {

    private final IDashboardService dashboardService;

    public DashBoardController(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Endpoint chính để FE gọi
    @GetMapping("/report")
    public DashboardReportDTO getReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,

            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String carType
    ) {
        return dashboardService.getDashboardReport(startDate, endDate, brand, carType);
    }
}
