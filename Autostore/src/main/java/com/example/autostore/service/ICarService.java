package com.example.autostore.service;

import com.example.autostore.dto.admin.CarRequestDTO;
import com.example.autostore.dto.admin.CarResponseDTO;
import com.example.autostore.dto.user.FeaturedCarDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICarService {

    Page<CarResponseDTO> findByKeyword(String keyword, Integer carId, Integer page, Integer pageSize);

    CarResponseDTO findById(Integer id);

    CarResponseDTO createCar(CarRequestDTO dto);

    CarResponseDTO updateCar(Integer id, CarRequestDTO dto);

    void deleteById(Integer id);
    List<FeaturedCarDTO> getFeaturedCars();
}
