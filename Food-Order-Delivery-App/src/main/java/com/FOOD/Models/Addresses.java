package com.FOOD.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String streetName;
    private String city;
    private String state;
    private String zipCode;
    private String country;


}
