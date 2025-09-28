package com.example.autostore.service;

import com.example.autostore.dto.admin.CarTypeDTO;
import com.example.autostore.model.CarType;
import com.example.autostore.repository.ICarTypeRepository;
import com.example.autostore.service.ICarTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarTypeServiceImpl implements ICarTypeService {

    private final ICarTypeRepository repo;

    public CarTypeServiceImpl(ICarTypeRepository repo) {
        this.repo = repo;
    }

    private CarTypeDTO toDTO(CarType type) {
        return new CarTypeDTO(type.getCarTypeId(), type.getTypeName());
    }

    @Override
    public List<CarTypeDTO> findAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarTypeDTO findById(Integer id) {
        return repo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public CarTypeDTO create(CarTypeDTO dto) {
        CarType ct = new CarType();
        ct.setTypeName(dto.getTypeName());
        return toDTO(repo.save(ct));
    }

    @Override
    public CarTypeDTO update(Integer id, CarTypeDTO dto) {
        CarType ct = repo.findById(id).orElseThrow();
        ct.setTypeName(dto.getTypeName());
        return toDTO(repo.save(ct));
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Page<CarTypeDTO> findByKeyword(String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("carTypeId").descending());
        Page<CarType> result = (keyword == null || keyword.isBlank())
                ? repo.findAll(pageable)
                : repo.findByTypeNameContainingIgnoreCase(keyword, pageable);
        return result.map(this::toDTO);
    }
}
