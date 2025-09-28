// src/main/java/com/example/autostore/controller/admin/CarController.java
package com.example.autostore.controller.admin;

import com.example.autostore.dto.admin.CarRequestDTO;
import com.example.autostore.dto.admin.CarResponseDTO;
import com.example.autostore.service.IBrandService;
import com.example.autostore.service.ICarService;
import com.example.autostore.service.ICarTypeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {

    private final ICarService carService;
    private final IBrandService brandService;
    private final ICarTypeService carTypeService;


    public CarController(ICarService carService, IBrandService brandService, ICarTypeService carTypeService) {
        this.carService = carService;
        this.brandService = brandService;
        this.carTypeService = carTypeService;
    }

    @GetMapping
    public Map<String, Object> getCars(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer carId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Page<CarResponseDTO> carPage = carService.findByKeyword(keyword, carId, page, pageSize);

        Map<String, Object> res = new HashMap<>();
        res.put("content", carPage.getContent());
        res.put("totalElements", carPage.getTotalElements());
        res.put("totalPages", carPage.getTotalPages());
        res.put("page", page);
        res.put("pageSize", pageSize);

        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Integer id) {
        CarResponseDTO dto = carService.findById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createCar(@Valid @ModelAttribute CarRequestDTO dto,
                                       BindingResult br) {
        if (br.hasErrors()) return ResponseEntity.badRequest().body(toErrorMap(br));
        return ResponseEntity.ok(carService.createCar(dto));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateCar(@PathVariable Integer id,
                                       @Valid @ModelAttribute CarRequestDTO dto,
                                       BindingResult br) {
        if (br.hasErrors()) return ResponseEntity.badRequest().body(toErrorMap(br));
        return ResponseEntity.ok(carService.updateCar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCar(@PathVariable Integer id) {
        carService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }

    private Map<String, String> toErrorMap(BindingResult br) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError e : br.getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        return errors;
    }
}
