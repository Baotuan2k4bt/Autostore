package com.example.autostore.controller.admin;

import com.example.autostore.Enum.PaymentStatus;
import com.example.autostore.dto.admin.PageResponse;
import com.example.autostore.dto.admin.PaymentDTO;

import com.example.autostore.mapper.PaymentMapper;
import com.example.autostore.model.Booking;
import com.example.autostore.model.Payment;
import com.example.autostore.service.IBookingService;
import com.example.autostore.service.IPaymentService;
import com.example.autostore.service.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/admin/payments")
public class PaymentController {

    private final IPaymentService paymentService;
    private final IBookingService bookingService;

    private final VnPayService vnPayService;

    public PaymentController(IPaymentService paymentService, IBookingService bookingService, VnPayService vnPayService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
        this.vnPayService = vnPayService;
    }

    // ✅ Danh sách (phân trang + tìm kiếm + filter) → Trả về PageResponse DTO
    @GetMapping
    public PageResponse<PaymentDTO> getPayments(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) PaymentStatus status,
            @RequestParam(required = false) Integer customerId,
            @PageableDefault(size = 10, sort = "paymentId") Pageable pageable
    ) {
        Page<PaymentDTO> page = paymentService.getPayments(keyword, status, customerId, pageable)
                .map(PaymentMapper::toDTO);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    // ✅ Lấy chi tiết theo ID
    @GetMapping("/{id}")
    public PaymentDTO getPaymentById(@PathVariable Integer id) {
        Payment payment = paymentService.getPaymentById(id);
        return PaymentMapper.toDTO(payment);
    }

    // ✅ Cập nhật Payment (Admin chỉnh sửa, không tạo mới từ đầu)
    @PostMapping
    public PaymentDTO savePayment(@RequestBody PaymentDTO dto) {
        Booking booking = bookingService.getBookingById(dto.getBookingId());
        Payment payment = PaymentMapper.toEntity(dto, booking);
        return PaymentMapper.toDTO(paymentService.savePayment(payment));
    }

    // ✅ Xóa Payment
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Integer id) {
        paymentService.deletePayment(id);
    }

    // ✅ Đổi trạng thái Payment
    @PutMapping("/{id}/status")
    public PaymentDTO updatePaymentStatus(
            @PathVariable Integer id,
            @RequestParam PaymentStatus status
    ) {
        return PaymentMapper.toDTO(paymentService.updatePaymentStatus(id, status));
    }


    @GetMapping("/create")
    public ResponseEntity<?> createPayment(@RequestParam("amount") long amount,
                                           @RequestParam("orderInfo") String orderInfo,
                                           HttpServletRequest request) throws UnsupportedEncodingException {
        String paymentUrl = vnPayService.createPaymentUrl(amount, orderInfo, request);
        return ResponseEntity.ok(Map.of("paymentUrl", paymentUrl));
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<?> vnpayReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements();) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                fields.put(fieldName, fieldValue);
            }
        }

        boolean valid = vnPayService.validateReturn(fields);

        if (valid) {
            String responseCode = fields.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                return ResponseEntity.ok("Payment success!");
            } else {
                return ResponseEntity.badRequest().body("Payment failed: " + responseCode);
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid checksum");
        }
    }

}
