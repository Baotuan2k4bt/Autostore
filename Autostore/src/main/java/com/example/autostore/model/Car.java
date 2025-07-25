package com.example.autostore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;



@Entity
@Table(name ="Car")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String carName;
    private String carType;

    @OneToOne(mappedBy = "car",cascade  =CascadeType.ALL)
    private CarDetail carDetail ;

    @OneToMany(mappedBy = "car" ,cascade =CascadeType.ALL)
    private List<CarImage> carImages ;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Pricing> pricing;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(
            name = "carServices", // tên bảng trung gian
            joinColumns = @JoinColumn(name = "carId"),
            inverseJoinColumns = @JoinColumn(name = "serviceId")
    )
    private List<CarService> services;
}
