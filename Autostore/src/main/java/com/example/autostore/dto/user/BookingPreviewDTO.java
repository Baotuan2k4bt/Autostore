package com.example.autostore.dto.user;

public class BookingPreviewDTO {
    private int rentalDays;       // số ngày thuê
    private Double dailyPrice;    // giá thuê/ngày
    private Double totalAmount;   // tổng tiền
    private Double depositAmount; // tiền cọc

    public BookingPreviewDTO(int rentalDays, Double dailyPrice, Double totalAmount, Double depositAmount) {
        this.rentalDays = rentalDays;
        this.dailyPrice = dailyPrice;
        this.totalAmount = totalAmount;
        this.depositAmount = depositAmount;
    }
    public BookingPreviewDTO() {}

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
