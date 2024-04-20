package com.soares.hudson.springcrude.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geo {
    private String lat;
    private String lng;
}
