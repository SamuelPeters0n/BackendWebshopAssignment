package com.example.backendwebshopassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

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

    public CustomerOrder(Customer customer, List<Item> itemList) {
        this.customer = customer;
        this.itemList = itemList;
    }


}
