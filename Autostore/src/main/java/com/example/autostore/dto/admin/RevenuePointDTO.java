package com.example.autostore.dto.admin;


public class RevenuePointDTO {
    private String month;   // "2025-09"
    private Double revenue;

    public RevenuePointDTO(String month, Double revenue) {
        this.month = month;
        this.revenue = revenue;
    }
    public RevenuePointDTO() {}


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
}