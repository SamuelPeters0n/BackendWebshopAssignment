package com.example.backendwebshopassignment.repository;

import com.example.backendwebshopassignment.models.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<CustomerOrder, Long> {
}
