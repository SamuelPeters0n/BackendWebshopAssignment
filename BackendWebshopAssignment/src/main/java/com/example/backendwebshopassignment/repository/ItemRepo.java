package com.example.backendwebshopassignment.repository;

import com.example.backendwebshopassignment.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
