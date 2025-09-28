package com.example.autostore.service.user.implement;

import com.example.autostore.Enum.BookingStatus;
import com.example.autostore.dto.user.ReviewDTO;
import com.example.autostore.model.Booking;
import com.example.autostore.model.Customer;
import com.example.autostore.model.Review;
import com.example.autostore.repository.IBookingRepository;
import com.example.autostore.repository.ICustomerRepository;
import com.example.autostore.repository.ReviewRepository;
import com.example.autostore.service.user.interfaces.IReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final IBookingRepository bookingRepository;
    private final ICustomerRepository customerRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         IBookingRepository bookingRepository,
                         ICustomerRepository customerRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ReviewDTO addReview(Integer bookingId, Integer customerId, int rating, String comment) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking không tồn tại"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer không tồn tại"));

        if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new RuntimeException("Chỉ được review khi booking đã hoàn thành");
        }

        Review review = new Review();
        review.setBooking(booking);
        review.setCustomer(customer);
        review.setRating(rating);
        review.setComment(comment);

        Review saved = reviewRepository.save(review);

        return new ReviewDTO(
                saved.getReviewId(),
                customer.getCustomerName(),
                booking.getCar().getCarName(),
                saved.getRating(),
                saved.getComment()
        );
    }


    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByCar(Integer carId) {
        return reviewRepository.findByBookingCarCarId(carId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByCustomer(Integer customerId) {
        return reviewRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Hàm dùng chung để convert Review → ReviewDTO
    private ReviewDTO mapToDTO(Review r) {
        return new ReviewDTO(
                r.getReviewId(),
                r.getCustomer().getCustomerName(),
                r.getBooking().getCar().getCarName(),
                r.getRating(),
                r.getComment()
        );
    }
}
