package com.example.autostore.controller.admin;

import com.example.autostore.dto.admin.CarTypeDTO;
import com.example.autostore.service.ICarTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cartypes")
@CrossOrigin(origins = "*")
public class CarTypeController {

    private final ICarTypeService service;

    public CarTypeController(ICarTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<CarTypeDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarTypeDTO> getById(@PathVariable Integer id) {
        CarTypeDTO dto = service.findById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CarTypeDTO> create(@Valid @RequestBody CarTypeDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarTypeDTO> update(@PathVariable Integer id, @Valid @RequestBody CarTypeDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }
    @GetMapping("/page")
    public Map<String, Object> getPage(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        var carTypePage = service.findByKeyword(keyword, page, pageSize);

        return Map.of(
                "content", carTypePage.getContent(),
                "totalElements", carTypePage.getTotalElements(),
                "totalPages", carTypePage.getTotalPages(),
                "page", page,
                "pageSize", pageSize
        );
    }
}

