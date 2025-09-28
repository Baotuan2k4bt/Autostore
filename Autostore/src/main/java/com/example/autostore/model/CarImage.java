package com.example.autostore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "CarImage")
public class CarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Column(name = "image_path", nullable = false) // đổi tên cột trong DB
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    public CarImage() {}

    public CarImage(String imagePath, Car car) {
        this.imagePath = imagePath;
        this.car = car;
    }

    // ===== Getters/Setters =====
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
