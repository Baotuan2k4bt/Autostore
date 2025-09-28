package com.example.autostore.dto.user;

public class ReviewDTO {
    private Long reviewId;
    private String customerName;
    private String carName;
    private int rating;
    private String comment;

    public ReviewDTO() {}

    public ReviewDTO(Long reviewId, String customerName, String carName, int rating, String comment) {
        this.reviewId = reviewId;
        this.customerName = customerName;
        this.carName = carName;
        this.rating = rating;
        this.comment = comment;
    }

    // getters & setters
    public Long getReviewId() { return reviewId; }
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
