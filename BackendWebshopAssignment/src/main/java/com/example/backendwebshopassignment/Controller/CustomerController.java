package com.example.backendwebshopassignment.Controller;


import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public @ResponseBody List<Customer> allCustomers(){return customerRepo.findAll();}

    @RequestMapping("/customers/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable long id) {
    return customerRepo.findById(id).orElse(null);
    }

    @GetMapping("/addCustomer")
    public @ResponseBody String addCustomer(@RequestParam String name, @RequestParam String ssn){
        Customer customer = new Customer(name, ssn);
        customerRepo.save(customer);
        return "Customer " + customer.getName() + " has been saved.";
    }

    @GetMapping("/addCustomer/form")
    public String addCostumerForm() {
        return "addCustomer";
    }


}
