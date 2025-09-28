package com.example.autostore.dto.admin;

import com.example.autostore.Enum.CarStatus;
import lombok.Getter;

@Getter
public class FleetCarDTO {
    private Integer carId;
    private String carName;
    private String imageUrl;
    private String location;
    private CarStatus status;


    public FleetCarDTO() {
    }

    public FleetCarDTO(Integer carId, String carName, String imageUrl, String location, CarStatus status) {
        this.carId = carId;
        this.carName = carName;
        this.imageUrl = imageUrl;
        this.location = location;
        this.status = status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}