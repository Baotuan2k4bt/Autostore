package com.example.autostore.service.user.interfaces;

import com.example.autostore.dto.user.CarUserDTO;

import java.util.List;

public interface ICarUserService {
    List<CarUserDTO> getAllCars();
    CarUserDTO getCarById(Integer id);
}
