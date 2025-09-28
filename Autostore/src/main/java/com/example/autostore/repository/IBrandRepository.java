package com.example.autostore.repository;

import com.example.autostore.model.Brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findByBrandName(String brandName);
    Page<Brand> findByBrandNameContainingIgnoreCase(String keyword, Pageable pageable);

}
