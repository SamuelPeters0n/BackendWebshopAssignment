package com.example.backendwebshopassignment.repository;

import com.example.backendwebshopassignment.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
