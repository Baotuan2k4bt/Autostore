package com.example.autostore.controller.users;

import com.example.autostore.dto.user.CarUserDTO;
import com.example.autostore.service.user.implement.CarUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/user/cars")
public class CarUserController {

    private final CarUserService carUserService;

    public CarUserController(CarUserService carUserService) {
        this.carUserService = carUserService;
    }


    @GetMapping
    public ResponseEntity<List<CarUserDTO>> getAllCars() {
        return ResponseEntity.ok(carUserService.getAllCars());
    }
    @GetMapping("/{id}")
    public CarUserDTO getCarById(@PathVariable Integer id) {
        return carUserService.getCarById(id);
    }
}
