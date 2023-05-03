package com.example.backendwebshopassignment.Controller;

import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    // Convert object to JsonString
    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo customerRepo;

    @BeforeEach
    public void init() {
        Customer c1 = new Customer(1L, "test1", "1111");
        Customer c2 = new Customer(2L, "test2", "2222");
        Customer c3 = new Customer(3L, "test3", "3333");
        Customer c4 = new Customer(4L, "test4", "4444");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(customerRepo.findById(2L)).thenReturn(Optional.of(c2));
        when(customerRepo.findById(3L)).thenReturn(Optional.of(c3));
        when(customerRepo.findById(4L)).thenReturn(Optional.of(c4));

        when(customerRepo.findAll()).thenReturn(Arrays.asList(c1, c2, c3, c4));
    }

    @Test
    public void findCustomerById() throws Exception {

        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(customerRepo.findById(1L).orElse(null))));

    }

    @Test
    public void findCustomers() throws Exception {
        this.mockMvc.perform(get("/customers"))
                .andExpectAll(status().isOk())
                .andExpect(content().json(asJsonString(customerRepo.findAll())));
    }

    @Test
    public void testAddCustomer() throws Exception {
        this.mockMvc.perform(get("/addCustomer?name=testPerson&ssn=9999"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Customer testPerson has been saved.")));


    }
}

