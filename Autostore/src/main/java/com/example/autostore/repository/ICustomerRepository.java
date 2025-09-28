package com.example.autostore.repository;

import com.example.autostore.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer> findByCustomerNameContainingIgnoreCase(String keyword, Pageable pageable);
}
