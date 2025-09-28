package com.example.autostore.repository;


import com.example.autostore.model.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarImageRepository extends JpaRepository<CarImage, Integer> {

}
