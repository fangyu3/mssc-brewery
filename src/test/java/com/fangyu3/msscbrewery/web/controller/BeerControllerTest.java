package com.fangyu3.msscbrewery.web.controller;

import com.fangyu3.msscbrewery.services.BeerService;
import com.fangyu3.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Because we are using WebMvcTest instead of SpringBootTest
    @MockBean
    private BeerService beerService;

    private BeerDto beerDto;

    @BeforeEach
    public void setUp() {
        beerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Galaxy cat")
                .beerStyle("ALE")
                .upc(3333333333L)
                .build();
    }

    @Test
    void getBeer() throws Exception {

        // Setup mock
        given(beerService.getBeerById(any(UUID.class))).willReturn(beerDto);

        // Peform GET request
        mockMvc.perform(get("/api/v1/beer/" + beerDto.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(beerDto.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Galaxy cat")));
    }

    @Test
    void saveBeer() throws Exception {

        beerDto.setId(null);
        given(beerService.saveBeer(any())).willReturn(beerDto);

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        System.out.println(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                        .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        UUID id = beerDto.getId();
        beerDto.setId(null);

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(beerDtoJson))
                            .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(),any());
    }

    @Test
    void deleteBeer() {
    }
}