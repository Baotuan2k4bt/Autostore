package com.example.autostore.mapper;

import com.example.autostore.dto.admin.CarResponseDTO;
import com.example.autostore.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "carId", target = "id")
    @Mapping(source = "carDetail.engine", target = "engine")
    @Mapping(source = "carDetail.fuelType", target = "fuelType")
    @Mapping(source = "carDetail.seatCount", target = "seatCount")
    @Mapping(source = "carDetail.year", target = "year")
    @Mapping(source = "carDetail.color", target = "color")
    @Mapping(source = "carDetail.licensePlate", target = "licensePlate")
    @Mapping(source = "brand.brandId", target = "brandId")
    @Mapping(source = "brand.brandName", target = "brandName")
    @Mapping(source = "carType.carTypeId", target = "carTypeId")
    @Mapping(source = "carType.typeName", target = "carTypeName")
    @Mapping(target = "primaryImage", expression = "java(car.getImageUrl())")

    CarResponseDTO toDTO(Car car);
}