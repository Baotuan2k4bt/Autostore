package com.example.autostore.service;

import com.example.autostore.Enum.CarStatus;
import com.example.autostore.dto.admin.CarRequestDTO;
import com.example.autostore.dto.admin.CarResponseDTO;
import com.example.autostore.dto.user.FeaturedCarDTO;
import com.example.autostore.exception.FileStorageException;
import com.example.autostore.mapper.CarMapper;
import com.example.autostore.model.*;
import com.example.autostore.repository.IBrandRepository;
import com.example.autostore.repository.ICarRepository;
import com.example.autostore.repository.ICarTypeRepository;
import com.example.autostore.service.FileUploadService;
import com.example.autostore.service.ICarService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements ICarService {

    private final ICarRepository carRepo;
    private final IBrandRepository brandRepo;
    private final ICarTypeRepository carTypeRepo;
    private final FileUploadService fileUploadService;
    private final CarMapper carMapper;

    public CarServiceImpl(ICarRepository carRepo,
                          IBrandRepository brandRepo,
                          ICarTypeRepository carTypeRepo,
                          FileUploadService fileUploadService,
                          CarMapper carMapper) {
        this.carRepo = carRepo;
        this.brandRepo = brandRepo;
        this.carTypeRepo = carTypeRepo;
        this.fileUploadService = fileUploadService;
        this.carMapper = carMapper;
    }

    @Override
    public Page<CarResponseDTO> findByKeyword(String keyword, Integer carId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdDate").descending());
        return carRepo.findByKeyword(keyword, carId, pageable).map(carMapper::toDTO);
    }

    @Override
    public CarResponseDTO findById(Integer id) {
        return carRepo.findById(id).map(carMapper::toDTO).orElse(null);
    }

    @Override
    public CarResponseDTO createCar(CarRequestDTO dto) {
        return null;
    }

    @Override
    public CarResponseDTO updateCar(Integer id, CarRequestDTO dto) {
        return null;
    }


    @Override
    public void deleteById(Integer id) {
        carRepo.deleteById(id);
    }

    @Override
    public List<FeaturedCarDTO> getFeaturedCars() {

        return carRepo.findFeaturedCars().stream().map(car -> {
            // Lấy giá theo ngày (unit = "DAY")
            Double price = car.getPricing().stream()
                    .filter(p -> "DAY".equalsIgnoreCase(p.getUnit()))
                    .map(Pricing::getPrice)
                    .map(BigDecimal::doubleValue)
                    .findFirst()
                    .orElse(0.0);

            return new FeaturedCarDTO(
                    car.getCarId(),
                    car.getCarName(),
                    car.getBrand().getBrandName(),
                    car.getImageUrl(),
                    price
            );
        }).toList();
    }


    // Helper method
    private String uploadFileSafe(MultipartFile file) {
        try {
            return fileUploadService.uploadFile(file);
        } catch (Exception e) {
            throw new FileStorageException("Upload failed: " + file.getOriginalFilename(), e);
        }
    }
}
