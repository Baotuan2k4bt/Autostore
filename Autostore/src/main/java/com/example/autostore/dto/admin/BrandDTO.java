package com.example.autostore.dto.admin;

import jakarta.validation.constraints.NotBlank;

public class BrandDTO {

    private Integer brandId;

    @NotBlank(message = "Tên thương hiệu không được để trống")
    private String brandName;

    private String description;

    private String logoUrl; // chỉ lưu đường dẫn ảnh

    public BrandDTO() {}

    public BrandDTO(Integer brandId, String brandName, String description, String logoUrl) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.description = description;
        this.logoUrl = logoUrl;
    }

    // getters + setters
    public Integer getBrandId() { return brandId; }
    public void setBrandId(Integer brandId) { this.brandId = brandId; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}
