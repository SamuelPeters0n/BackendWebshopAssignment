package com.example.backendwebshopassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
