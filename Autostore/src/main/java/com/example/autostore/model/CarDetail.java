package com.example.autostore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CarDetail")
public class CarDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carDetailId;

    private String engine;
    private String fuelType;
    private int seatCount;
    private int year;
    private String color;
    private String licensePlate;
    private String description;
    private String transmission;

    @ManyToOne
    @JoinColumn(name = "brandId", referencedColumnName = "brandId")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "carId", referencedColumnName = "carId")
    private Car car;

    // Constructors
    public CarDetail() {}

    public CarDetail(Integer carDetailId, String engine, String fuelType, int seatCount, int year,
                     String color, String licensePlate, String description, Brand brand, Car car , String transmission) {
        this.carDetailId = carDetailId;
        this.engine = engine;
        this.fuelType = fuelType;
        this.seatCount = seatCount;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.description = description;
        this.brand = brand;
        this.car = car;
        this.transmission = transmission;
    }

    // Standard getters and setters
    public Integer getCarDetailId() {
        return carDetailId;
    }

    public void setCarDetailId(Integer carDetailId) {
        this.carDetailId = carDetailId;
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

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
