package com.example.autostore.controller.admin;

import com.example.autostore.Enum.BookingStatus;

import com.example.autostore.dto.admin.BookingDTO;
import com.example.autostore.mapper.BookingMapper;
import com.example.autostore.model.Booking;
import com.example.autostore.service.IBookingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/bookings")
public class BookingController {

    private final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    // ✅ Danh sách + tìm kiếm + phân trang
    @GetMapping
    public ResponseEntity<?> getBookings(
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 5, sort = "bookingId") Pageable pageable
    ) {
        Page<BookingDTO> page = bookingService.getBookings(keyword, pageable)
                .map(BookingMapper::toDTO);

        return ResponseEntity.ok(Map.of(
                "content", page.getContent(),
                "pageNumber", page.getNumber(),
                "pageSize", page.getSize(),
                "totalElements", page.getTotalElements(),
                "totalPages", page.getTotalPages(),
                "first", page.isFirst(),
                "last", page.isLast()
        ));
    }


    //  Chi tiết booking
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer id) {
        Booking booking = bookingService.getBookingById(id);
        return booking != null
                ? ResponseEntity.ok(BookingMapper.toDTO(booking))
                : ResponseEntity.notFound().build();
    }

    //  Cập nhật trạng thái
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Integer id,
                                                          @RequestParam BookingStatus status) {
        Booking booking = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    //  Xóa booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
