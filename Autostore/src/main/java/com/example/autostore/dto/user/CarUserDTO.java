package com.example.autostore.dto.user;

import com.example.autostore.Enum.CarStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CarUserDTO {
    private Integer carId;
    private String carName;
    private String imageUrl;      // ảnh chính (Car.imageUrl)
    private List<String> gallery; // danh sách ảnh phụ (CarImage.imagePath)
    private String brandName;
    private String typeName;
    private Double price;
    private Integer seats;
    private String fuelType;
    private String transmission;
    private String location;
    private int rating;
    private String engine;
    private CarStatus status;

    private int year;
    private String color;
    private String licensePlate;
    private String description;
    private LocalDateTime createdDate;
    private Double latitude;
    private Double longitude;
    private boolean isFeatured;


    public CarUserDTO() {
    }

    public CarUserDTO(Integer carId, String carName, String imageUrl, List<String> gallery, String brandName, String typeName,
                      Double price, Integer seats, String fuelType, String transmission, String location, int rating, CarStatus status, String engine) {
        this.carId = carId;
        this.carName = carName;
        this.imageUrl = imageUrl;
        this.gallery = gallery;
        this.brandName = brandName;
        this.typeName = typeName;
        this.price = price;
        this.seats = seats;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.location = location;
        this.rating = rating;
        this.status = status;
        this.engine = engine;
    }

    public CarUserDTO(Integer carId, String carName, String imageUrl, List<String> gallery, String brandName, String typeName, Double price, Integer seats, String fuelType, String transmission, String location, int rating, String engine, CarStatus status, int year, String color, String licensePlate, String description, LocalDateTime createdDate, Double latitude, Double longitude, boolean isFeatured) {
        this.carId = carId;
        this.carName = carName;
        this.imageUrl = imageUrl;
        this.gallery = gallery;
        this.brandName = brandName;
        this.typeName = typeName;
        this.price = price;
        this.seats = seats;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.location = location;
        this.rating = rating;
        this.engine = engine;
        this.status = status;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.description = description;
        this.createdDate = createdDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFeatured = isFeatured;
    }

    public CarUserDTO(Integer carId, String carName, String imageUrl, List<String> gallery, String brandName, String typeName, double price, int seats, String fuelType, String transmission, String location, int rating, CarStatus carStatus, CarStatus status, int year, String color, String licensePlate, String description, LocalDateTime createdDate, Double latitude, Double longitude, boolean featured) {
        this.carId = carId;
        this.carName = carName;
        this.imageUrl = imageUrl;
        this.gallery = gallery;
        this.brandName = brandName;
        this.typeName = typeName;
        this.price = price;
        this.seats = seats;
        this.fuelType = fuelType;
        this.transmission = transmission;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getRating() {
        return rating;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
