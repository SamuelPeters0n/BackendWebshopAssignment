package com.example.backendwebshopassignment;

import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackendWebshopAssignmentApplication {

    //Database and user needs to be created in SQL, use following code:
    //-------------------------------------------------------------
    // create database BackendWebshopDatabase;
    // create user 'springuser@localhost' identified by '12345678';
    // grant all on *.* to 'springuser@localhost';
    //-------------------------------------------------------------
    public static void main(String[] args) {
        SpringApplication.run(BackendWebshopAssignmentApplication.class, args);
    }


   /* @Bean
    public CommandLineRunner bootstrap(CustomerRepo customerRepo, ItemRepo itemRepo, OrderRepo orderRepo) {

        return (args) -> {
            Customer c1 = new Customer("Samuel", "12345678");
            Customer c2 = new Customer("Frida", "12345679");
            Customer c3 = new Customer("Robin", "8765432");
            Customer c4 = new Customer("Svante", "123465432");

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);
            customerRepo.save(c4);


            Item i1 = new Item("produkt1", 99.50);
            Item i2 = new Item("produkt2", 50.50);
            Item i3 = new Item("produkt3", 1.50);
            Item i4 = new Item("produkt4", 66.50);

            itemRepo.save(i1);
            itemRepo.save(i2);
            itemRepo.save(i3);
            itemRepo.save(i4);

            List<Item> il1 = Arrays.asList(i1, i2);
            List <Item> il2 = Arrays.asList(i1, i4);
            List<Item> il3 = Arrays.asList(i3, i3);

            CustomerOrder o1 = new CustomerOrder(c1, il1, LocalDateTime.now());
            CustomerOrder o2 = new CustomerOrder(c2, il2, LocalDateTime.now());
            CustomerOrder o3 = new CustomerOrder(c3, il3, LocalDateTime.now());

            orderRepo.save(o1);
            orderRepo.save(o2);
            orderRepo.save(o3);

            System.out.println("Bootstrap Loaded");
        };
    }*/



}
