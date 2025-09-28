package com.example.autostore.model;

import com.example.autostore.Enum.CarStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    @Column(nullable = false)
    private String carName;


    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private Integer quantity;

    private String location;

    private LocalDateTime createdDate;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false)
    public boolean isFeatured = false;


    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private CarDetail carDetail;

    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CarImage> carImages;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pricing> pricing;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "carTypeId", nullable = false)
    private CarType carType;

    // ===== Constructors =====
    public Car() {
    }

    public Car(Integer carId, String carName, String imageUrl, CarStatus status, Integer quantity, String location, LocalDateTime createdDate, Double latitude, Double longitude, CarDetail carDetail, Brand brand, List<CarImage> carImages, List<Pricing> pricing, List<Booking> bookings, CarType carType, boolean isFeatured) {
        this.carId = carId;
        this.carName = carName;
        this.imageUrl = imageUrl;
        this.status = status;
        this.quantity = quantity;
        this.location = location;
        this.createdDate = createdDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.carDetail = carDetail;
        this.brand = brand;
        this.carImages = carImages;
        this.pricing = pricing;
        this.bookings = bookings;
        this.carType = carType;
        this.isFeatured = isFeatured;
    }

    // ===== Standard getters & setters =====
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public CarDetail getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(CarDetail carDetail) {
        this.carDetail = carDetail;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<CarImage> getCarImages() {
        return carImages;
    }

    public void setCarImages(List<CarImage> carImages) {
        this.carImages = carImages;
    }

    public List<Pricing> getPricing() {
        return pricing;
    }

    public void setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
