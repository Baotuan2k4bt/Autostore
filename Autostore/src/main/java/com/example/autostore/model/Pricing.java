package com.example.autostore.model;


import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name="Pricing")
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pricingId;
    private String unit;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "carID")
    private Car car;

    public Pricing() {
    }
    public Pricing(Integer pricingId, String unit, BigDecimal price, Car car) {
        this.pricingId = pricingId;
        this.unit = unit;
        this.price = price;
        this.car = car;
    }

    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
