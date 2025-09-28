package com.example.autostore.dto.user;

public class FeaturedCarDTO{
    private Integer id;
    private String name;
    private String brand;
    private String imageUrl;
    private Double price;

    public FeaturedCarDTO() {
    }

    public FeaturedCarDTO(Integer id, String name, String brand, String imageUrl, Double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
