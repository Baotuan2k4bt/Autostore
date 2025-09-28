package com.example.autostore.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CarLocationDTO {
    private Integer carId;
    private String carName;
    private String status;
    private Double latitude;
    private Double longitude;


    public CarLocationDTO() {
    }

    public CarLocationDTO(Integer carId, String carName, String name, Double latitude, Double longitude) {
        this.carId = carId;
        this.carName = carName;
        this.status = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CarLocationDTO(Integer carId, String carName, double latitude, double longitude, String name) {
        this.carId = carId;
        this.carName = carName;
        this.status = name;
        this.latitude = latitude;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}

