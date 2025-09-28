package com.example.autostore.service;

import com.example.autostore.dto.admin.BrandDTO;
import org.springframework.data.domain.Page;


import java.util.List;

public interface IBrandService {
    List<BrandDTO> findAll();
    BrandDTO findById(Integer id);
    BrandDTO create(BrandDTO dto);
    BrandDTO update(Integer id, BrandDTO dto);
    void delete(Integer id);
    Page<BrandDTO> findByKeyword(String keyword, int page, int pageSize);

}
