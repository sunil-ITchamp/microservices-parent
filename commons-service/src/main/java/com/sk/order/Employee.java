package com.sk.order;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    @OneToMany
    @JoinColumn(name = "address_id")
    private List<Address> address;

}
