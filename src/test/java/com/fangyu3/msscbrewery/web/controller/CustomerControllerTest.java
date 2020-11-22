package com.fangyu3.msscbrewery.web.controller;

import com.fangyu3.msscbrewery.services.CustomerService;
import com.fangyu3.msscbrewery.web.model.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    private CustomerDto customerDto;

    @BeforeEach
    private void setup() {
        customerDto = CustomerDto.builder()
                        .name("Tony")
                        .build();
    }

    @Test
    void getCustomer() {
    }

    @Test
    void saveCustomer() throws Exception {

        given(customerService.saveCustomer(any())).willReturn(customerDto);
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);
        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                        .andExpect(status().isCreated());
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}