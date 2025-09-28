package com.example.autostore.controller.admin;


import com.example.autostore.dto.admin.BookingDTO;
import com.example.autostore.mapper.BookingMapper;
import com.example.autostore.model.Booking;
import com.example.autostore.repository.IBookingRepository;
import com.example.autostore.util.QRCodeGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {

    private final IBookingRepository bookingRepository;
    private final ObjectMapper objectMapper;

    public AdminBookingController(IBookingRepository bookingRepository, ObjectMapper objectMapper) {
        this.bookingRepository = bookingRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> getBookingQRCode(@PathVariable Integer id) throws IOException, WriterException {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Map sang DTO
        BookingDTO dto = BookingMapper.toDTO(booking);

        // Bổ sung metadata
        Map<String, Object> qrPayload = new LinkedHashMap<>();
        qrPayload.put("booking", dto);
        qrPayload.put("generatedAt", LocalDateTime.now().toString());
        qrPayload.put("secureUrl", "https://carrental.com/booking/" + booking.getBookingId());

        // Convert sang JSON string
        String qrContent = objectMapper.writeValueAsString(qrPayload);

        // Sinh QR code PNG
        byte[] qrImage = QRCodeGenerator.generateQRCodeImage(qrContent, 300, 300);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=booking_" + booking.getBookingId() + "_qrcode.png")
                .body(qrImage);
    }
}
