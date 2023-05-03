package com.example.backendwebshopassignment.Controller;

import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.CustomerOrder;
import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.example.backendwebshopassignment.repository.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    // Convert object to JsonString
    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepo itemRepo;
    @MockBean
    private CustomerRepo customerRepo;
    @MockBean
    private OrderRepo orderRepo;

    @BeforeEach
    public void init() {
        Item i1 = new Item(1L, "Product1", 100);
        Item i2 = new Item(2L, "Product2", 100);
        Item i3 = new Item(3L, "Product3", 100);
        Item i4 = new Item(4L, "Product4", 100);

        when(itemRepo.findAll()).thenReturn(Arrays.asList(i1, i2, i3, i4));

        when(itemRepo.findById(1L)).thenReturn(Optional.of(i1));
        when(itemRepo.findById(2L)).thenReturn(Optional.of(i2));
        when(itemRepo.findById(3L)).thenReturn(Optional.of(i3));
        when(itemRepo.findById(4L)).thenReturn(Optional.of(i4));

        Customer c1 = new Customer(1L, "test1", "1111");
        Customer c2 = new Customer(2L, "test2", "2222");
        Customer c3 = new Customer(3L, "test3", "3333");
        Customer c4 = new Customer(4L, "test4", "4444");

        when(customerRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(customerRepo.findById(2L)).thenReturn(Optional.of(c2));
        when(customerRepo.findById(3L)).thenReturn(Optional.of(c3));
        when(customerRepo.findById(4L)).thenReturn(Optional.of(c4));

        CustomerOrder o1 = new CustomerOrder(1L, c1, List.of(i1), null);
        CustomerOrder o2 = new CustomerOrder(2L, c2, List.of(i2), null);
        CustomerOrder o3 = new CustomerOrder(3L, c3, List.of(i3), null);
        CustomerOrder o4 = new CustomerOrder(4L, c4, List.of(i4), null);

        when(orderRepo.findById(1L)).thenReturn(Optional.of(o1));
        when(orderRepo.findById(2L)).thenReturn(Optional.of(o2));
        when(orderRepo.findById(3L)).thenReturn(Optional.of(o3));
        when(orderRepo.findById(4L)).thenReturn(Optional.of(o4));
        when(orderRepo.findAll()).thenReturn(Arrays.asList(o1, o2, o3, o4));
    }

    @Test
    public void findOrders() throws Exception {
        this.mockMvc.perform(get("/orders"))
                .andExpectAll(status().isOk())
                .andExpect(content().json(asJsonString(orderRepo.findAll())));
    }

    @Test
    public void getCostumerOrder() throws Exception {
        this.mockMvc.perform(get("/orders/1"))
                .andExpectAll(status().isOk())
                .andExpect(content().json(asJsonString(orderRepo.findByCustomerId(1L))));
    }


    @Test
    public void BuyItem() throws Exception {
        this.mockMvc.perform(post("/items/1/buy/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("New order for product " +
                        itemRepo.findById(1L).orElse(null).getName() + " by customer "+ customerRepo.findById(1L).orElse(null).getName() +" has been saved.")));
    }
}

