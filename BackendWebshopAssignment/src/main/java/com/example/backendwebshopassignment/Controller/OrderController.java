package com.example.backendwebshopassignment.Controller;


import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final CustomerRepo customerRepo;
    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;

    public OrderController(CustomerRepo customerRepo, ItemRepo itemRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
    }

    @RequestMapping("/orders")
    public List<CustomerOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    @RequestMapping("/orders/{customerId}")
    public List<CustomerOrder> getCustomerOrder(@PathVariable Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    @PostMapping("/items/buy")
    public String addCustomer(@RequestBody CustomerOrder order){
        orderRepo.save(order);
        return "New order for " + order.getCustomer().getName() + " has been saved.";
    }

}
