package com.example.backendwebshopassignment.Controller;


import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private final ItemRepo itemRepo;
    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;

    public CustomerController(ItemRepo itemRepo, CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.itemRepo = itemRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    @RequestMapping("/customers")
    public List<Customer> allCustomers(){return customerRepo.findAll();}

    @RequestMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable long id) {
    return customerRepo.findById(id).orElse(null);
    }

    @PostMapping("/add_customer/postMap")
    public String addCustomer(@RequestBody Customer customer){
        customerRepo.save(customer);
        return "Customer " + customer.getName() + " has been saved.";
    }


}
