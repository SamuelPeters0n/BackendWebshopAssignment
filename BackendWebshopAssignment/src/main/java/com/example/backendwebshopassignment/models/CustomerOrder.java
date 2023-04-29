package com.example.backendwebshopassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToMany
    @JoinTable
    private List<Item> itemList;

    private LocalDateTime date;

    public CustomerOrder(Customer customer, List<Item> itemList, LocalDateTime date) {
        this.customer = customer;
        this.itemList = itemList;
        this.date = date;
    }


}
