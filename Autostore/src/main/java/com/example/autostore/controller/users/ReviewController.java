package com.example.autostore.controller.users;

import com.example.autostore.dto.user.ReviewDTO;
import com.example.autostore.service.user.implement.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Thêm review
    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(
            @RequestParam Integer bookingId,
            @RequestParam Integer customerId,
            @RequestParam int rating,
            @RequestParam String comment
    ) {
        return ResponseEntity.ok(reviewService.addReview(bookingId, customerId, rating, comment));
    }

    // Lấy review theo xe
    @GetMapping("/car/{carId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByCar(@PathVariable Integer carId) {
        return ResponseEntity.ok(reviewService.getReviewsByCar(carId));
    }

    // Lấy review theo khách hàng
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByCustomer(@PathVariable Integer customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customerId));
    }
    // Lấy tất cả review
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

}
