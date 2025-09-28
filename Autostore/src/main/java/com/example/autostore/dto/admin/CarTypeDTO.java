package com.example.autostore.dto.admin;

import jakarta.validation.constraints.NotBlank;

public class CarTypeDTO {
    private Integer id;

    @NotBlank(message = "Tên loại xe không được để trống")
    private String typeName;

    public CarTypeDTO() {}

    public CarTypeDTO(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
}
