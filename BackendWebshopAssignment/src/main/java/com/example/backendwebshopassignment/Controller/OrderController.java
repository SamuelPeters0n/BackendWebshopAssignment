package com.example.backendwebshopassignment.Controller;


import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;

    OrderController(CustomerRepo customerRepo, ItemRepo itemRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
    }

    @RequestMapping
    public List<CustomerOrder> getAllOrders() {
        return orderRepo.findAll();

    }

}
