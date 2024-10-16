package com.sk.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String house;
    private String street;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
