package com.example.autostore.controller.admin;

import com.example.autostore.dto.admin.BrandDTO;
import com.example.autostore.service.BrandServiceImpl;
import com.example.autostore.service.IBrandService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins = "*")
public class BrandController {

    private final IBrandService service;

    public BrandController(IBrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<BrandDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getById(@PathVariable Integer id) {
        BrandDTO dto = service.findById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
    @GetMapping("/page")
    public Map<String, Object> getBrandsPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword
    ) {
        Page<BrandDTO> brandPage = ((BrandServiceImpl) service).findByKeyword(keyword, page, pageSize);

        Map<String, Object> res = new HashMap<>();
        res.put("content", brandPage.getContent());
        res.put("totalElements", brandPage.getTotalElements());
        res.put("totalPages", brandPage.getTotalPages());
        res.put("page", page);
        res.put("pageSize", pageSize);

        return res;
    }


    // Thêm brand (có thể upload logo)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<BrandDTO> create(
            @Valid @ModelAttribute BrandDTO dto,
            @RequestParam(value = "logo", required = false) MultipartFile logo
    ) throws IOException {
        if (logo != null && !logo.isEmpty()) {
            return ResponseEntity.ok(((BrandServiceImpl) service).createWithLogo(dto, logo));
        }
        return ResponseEntity.ok(service.create(dto));
    }

    // Sửa brand (có thể update logo mới)
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<BrandDTO> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute BrandDTO dto,
            @RequestParam(value = "logo", required = false) MultipartFile logo
    ) throws IOException {
        if (logo != null && !logo.isEmpty()) {
            return ResponseEntity.ok(((BrandServiceImpl) service).updateWithLogo(id, dto, logo));
        }
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }
}
