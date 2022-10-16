package org.example.model;

import lombok.Data;

@Data
public class City {
    private int id;
    private String name;
    private int population;
    private String countryCode;
}