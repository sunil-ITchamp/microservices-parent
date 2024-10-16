package com.sk.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my-product-generator")
        @SequenceGenerator(name="my-product-generator", sequenceName = "product_id_seq")
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
    }
