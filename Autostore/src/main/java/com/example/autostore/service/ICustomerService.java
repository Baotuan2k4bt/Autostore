package com.example.autostore.service;

import com.example.autostore.Enum.CustomerStatus;
import com.example.autostore.model.Customer;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ICustomerService {
    Page<Customer> getPage(String keyword, int page, int pageSize);
    Optional<Customer> getById(Integer id);
    void delete(Integer id);
    Customer updateStatus(Integer id, CustomerStatus status);
}
