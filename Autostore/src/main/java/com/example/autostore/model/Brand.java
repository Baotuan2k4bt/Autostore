package com.example.autostore.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    @Column(nullable = false, unique = true)
    private String brandName;

    private String description;


    // Logo thương hiệu
    @Column(name = "logoUrl")
    private String logoUrl;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<CarDetail> carDetails;

    public Brand() {}

    public Brand(Integer brandId, String brandName, String description, String logoUrl) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.description = description;
        this.logoUrl = logoUrl;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<CarDetail> getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(List<CarDetail> carDetails) {
        this.carDetails = carDetails;
    }
}
