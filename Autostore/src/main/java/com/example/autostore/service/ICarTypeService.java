package com.example.autostore.service;

import com.example.autostore.dto.admin.CarTypeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICarTypeService {
    List<CarTypeDTO> findAll();
    CarTypeDTO findById(Integer id);
    CarTypeDTO create(CarTypeDTO dto);
    CarTypeDTO update(Integer id, CarTypeDTO dto);
    void delete(Integer id);
    Page<CarTypeDTO> findByKeyword(String keyword, int page, int pageSize);
}
