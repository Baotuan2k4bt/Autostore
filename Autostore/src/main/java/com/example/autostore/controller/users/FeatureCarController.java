package com.example.autostore.controller.users;

import com.example.autostore.dto.user.FeaturedCarDTO;
import com.example.autostore.service.CarServiceImpl;
import com.example.autostore.service.ICarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/cars")
public class FeatureCarController {
    private final ICarService carService;

    public FeatureCarController(ICarService carService) {
        this.carService = carService;
    }
    @GetMapping("/featured")
    public ResponseEntity<List<FeaturedCarDTO>> getFeaturedCars() {
        return ResponseEntity.ok(carService.getFeaturedCars());
    }
}
