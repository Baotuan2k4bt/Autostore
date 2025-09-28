package com.example.autostore.controller.admin;

import com.example.autostore.Enum.CustomerStatus;
import com.example.autostore.model.Customer;
import com.example.autostore.service.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ Lấy danh sách có phân trang + tìm kiếm
    @GetMapping("/page")
    public Map<String, Object> getPage(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = customerService.getPage(keyword, page, pageSize);

        return Map.of(
                "content", customerPage.getContent(),
                "totalElements", customerPage.getTotalElements(),
                "totalPages", customerPage.getTotalPages(),
                "page", page,
                "pageSize", pageSize
        );
    }

    // ✅ Lấy chi tiết khách hàng
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {
        return customerService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khách hàng với ID = " + id));
    }

    // ✅ Xóa khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Xóa khách hàng thành công"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Customer> updateStatus(
            @PathVariable Integer id,
            @RequestParam CustomerStatus status) {
        return ResponseEntity.ok(customerService.updateStatus(id, status));
    }

}
