package com.example.autostore.dto.admin;

public class BrandRatioDTO {
    private String brand;
    private Long count;

    public BrandRatioDTO(String brand, Long count) {
        this.brand = brand;
        this.count = count;
    }

    public BrandRatioDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}