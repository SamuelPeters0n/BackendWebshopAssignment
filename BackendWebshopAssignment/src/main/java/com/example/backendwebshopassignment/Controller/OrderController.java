package com.example.backendwebshopassignment.Controller;


import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
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
    public @ResponseBody List<CustomerOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    @RequestMapping("/orders/{customerId}")
    public @ResponseBody List<CustomerOrder> getCustomerOrder(@PathVariable Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    @PostMapping("/items/{itemId}/buy/{customerId}")
    public @ResponseBody String addOrder(@PathVariable Long itemId, @PathVariable Long customerId){
        Item item = itemRepo.findById(itemId).orElse(null);
        List<Item> itemList = Arrays.asList(item);
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if(customer == null || item == null){
            return "Customer or item does not exist";
        }
        else {
            CustomerOrder order = new CustomerOrder(customer, itemList, LocalDateTime.now());
            orderRepo.save(order);
        }

        return "New order for product " + item.getName() + " by customer " + customer.getName() + " has been saved.";
    }

    @GetMapping("/getCart")
    public String addOrderForm(@RequestParam Long orderId, Model model) {
        Iterable<Item> items = orderRepo.findById(orderId).orElse(null).getItemList();
        model.addAttribute("items", items);
        return "getCart";
    }
}
