package com.sk.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Product testProduct1;
    private Product testProduct2;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        testProduct1 = Product.builder().name("BundaBerg").description("Autralian").price(new BigDecimal(7)).build();
        testProduct2 = Product.builder().name("Peroni").description("Italian").price(new BigDecimal(10)).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createProduct() throws Exception {
        String contentString = objectMapper.writeValueAsString(testProduct1);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentString))
                        .andExpect(status().isOk());
    }

    @Test
    void getProductById() {
//        mockMvc.perform(get("/api/product/{id}","id",7))
//                        MockMvcRequestBuilders.get("/api/product")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(contentString))
//                .andExpect(status().isOk());
    }

    @Test
    void getAllProducts() {
    }
}