package com.example.autostore.repository;

import com.example.autostore.model.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICarTypeRepository extends JpaRepository<CarType, Integer> {
    Optional<CarType> findByTypeName(String typeName);
    Page<CarType> findByTypeNameContainingIgnoreCase(String keyword, Pageable pageable);
}
