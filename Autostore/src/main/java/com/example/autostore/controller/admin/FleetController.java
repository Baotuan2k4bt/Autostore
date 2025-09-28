package com.example.autostore.controller.admin;

import com.example.autostore.dto.admin.CarLocationDTO;
import com.example.autostore.dto.admin.FleetOverviewDTO;
import com.example.autostore.dto.admin.FleetCarDTO;
import com.example.autostore.service.user.implement.FleetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/fleet")
public class FleetController {

    private final FleetService fleetService;

    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    //  Thống kê tổng quan
    @GetMapping("/overview")
    public FleetOverviewDTO getOverview() {
        return fleetService.getOverview();
    }

    //  Danh sách toàn bộ xe
    @GetMapping("/cars")
    public List<FleetCarDTO> getAllCars() {
        return fleetService.getAllCars();
    }
    // API lấy danh sách vị trí xe
    @GetMapping("/tracking")
    public List<CarLocationDTO> getTracking() {
        return fleetService.getCarLocations();
    }

    // 🔹 API cập nhật vị trí xe (Realtime update)
    @PutMapping("/cars/{id}/location")
    public CarLocationDTO updateCarLocation(
            @PathVariable Integer id,
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        return fleetService.updateCarLocation(id, latitude, longitude);
    }
}
