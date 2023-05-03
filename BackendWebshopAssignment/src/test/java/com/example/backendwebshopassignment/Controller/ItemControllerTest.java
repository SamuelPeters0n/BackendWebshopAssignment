package com.example.backendwebshopassignment.Controller;

import com.example.backendwebshopassignment.models.Customer;
import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.CustomerRepo;
import com.example.backendwebshopassignment.repository.ItemRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    // Convert object to JsonString
    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepo itemRepo;

    @BeforeEach
    public void init() {
        Item i1 = new Item(1L, "Product1", 100);
        Item i2 = new Item(2L, "Product2", 100);
        Item i3 = new Item(3L, "Product3", 100);
        Item i4 = new Item(4L, "Product4", 100);

        when(itemRepo.findById(1L)).thenReturn(Optional.of(i1));
        when(itemRepo.findById(2L)).thenReturn(Optional.of(i2));
        when(itemRepo.findById(3L)).thenReturn(Optional.of(i3));
        when(itemRepo.findById(4L)).thenReturn(Optional.of(i4));

        when(itemRepo.findAll()).thenReturn(Arrays.asList(i1, i2, i3, i4));
    }

    @Test
    public void findItemById() throws Exception {

        this.mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(itemRepo.findById(1L).orElse(null))));


    }

    @Test
    public void findItems() throws Exception {
        this.mockMvc.perform(get("/items"))
                .andExpectAll(status().isOk())
                .andExpect(content().json(asJsonString(itemRepo.findAll())));
    }

    @Test
    public void testAddCustomer() throws Exception {
        this.mockMvc.perform(get("/addItem?name=mellanmjölk&price=9999"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Item \"mellanmjölk\" saved")));


    }
}

