package com.soares.hudson.springcrude.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String suite;  
    private String city;
    private String zipcode;
}
