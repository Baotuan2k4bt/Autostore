// src/main/java/com/example/autostore/dto/admin/CarRequestDTO.java
package com.example.autostore.dto.admin;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class CarRequestDTO {

    @NotBlank(message = "Trạng thái không được để trống (AVAILABLE/RENTED/MAINTENANCE)")
    private String status; // AVAILABLE | RENTED | MAINTENANCE

    @NotBlank(message = "Tên xe không được để trống")
    @Pattern(regexp = "^[\\p{L}0-9\\s-]+$", message = "Tên xe chỉ chứa chữ, số, khoảng trắng, dấu -")
    private String carName;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer quantity;

    @NotBlank(message = "Vị trí không được để trống")
    @Pattern(regexp = "^[\\p{L}0-9\\s-]+$", message = "Vị trí chỉ chứa chữ, số, khoảng trắng, dấu -")
    private String location;

    // ===== CarDetail =====
    @NotBlank(message = "Động cơ không được để trống")
    private String engine;

    @NotBlank(message = "Loại nhiên liệu không được để trống")
    private String fuelType;

    @NotNull(message = "Số ghế không được để trống")
    @Min(value = 1, message = "Số ghế phải >= 1")
    private Integer seatCount;

    @NotNull(message = "Năm sản xuất không được để trống")
    @Min(value = 1900, message = "Năm sản xuất không hợp lệ")
    private Integer year;

    @NotBlank(message = "Màu sắc không được để trống")
    private String color;

    @NotBlank(message = "Biển số xe không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9\\s-]+$", message = "Biển số chỉ chứa chữ, số, khoảng trắng, dấu -")
    private String licensePlate;

    // ===== Quan hệ =====
    @NotNull(message = "Thương hiệu không được để trống")
    private Integer brandId;

    @NotNull(message = "Loại xe không được để trống")
    private Integer carTypeId;

    // ===== Ảnh =====
    @Size(min = 1, max = 5, message = "Phải tải lên từ 1 đến 5 ảnh")
    private List<MultipartFile> images;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
