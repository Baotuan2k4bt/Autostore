package com.example.autostore.model;


import com.example.autostore.Enum.CustomerStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    // Thông tin cơ bản
    @Column(nullable = false, length = 100)
    private String customerName;

    @Column(nullable = false, unique = true, length = 100)
    private String customerEmail;

    @Column(nullable = false, unique = true, length = 20)
    private String customerPhone;

    @Column(length = 255)
    private String customerAddress;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    private String id_number; //CCCD
    private String license_number; // giấy phép lái xe


    // Liên kết với tài khoản hệ thống
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", unique = true)
    private AppUser appUser;

    // Thời gian tạo & cập nhật
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String customerEmail, String customerPhone, String customerAddress, LocalDate birthDate, CustomerStatus status, String id_number, String license_number, AppUser appUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.birthDate = birthDate;
        this.status = status;
        this.id_number = id_number;
        this.license_number = license_number;
        this.appUser = appUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer(Integer customerId, String customerName, String customerEmail, String customerPhone, String customerAddress, LocalDate birthDate, CustomerStatus status, AppUser appUser, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.birthDate = birthDate;
        this.status = status;
        this.appUser = appUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
