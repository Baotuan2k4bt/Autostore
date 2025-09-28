package com.example.autostore.model;

import jakarta.persistence.*;

@Entity
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carTypeId;

    @Column(nullable = false, unique = true)
    private String typeName;

    public CarType() {
    }

    public CarType(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
