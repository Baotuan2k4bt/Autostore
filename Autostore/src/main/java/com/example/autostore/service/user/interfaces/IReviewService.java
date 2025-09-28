package com.example.autostore.service.user.interfaces;

import com.example.autostore.dto.user.ReviewDTO;

import java.util.List;

public interface IReviewService {
    ReviewDTO addReview(Integer bookingId, Integer customerId, int rating, String comment);
    List<ReviewDTO> getAllReviews();
}
