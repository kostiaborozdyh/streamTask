package org.example.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Country {
    private String code;
    private String name;
    private String continent;
    private double surfaceArea;
    private int population;
    private double gnp;
    private int capital;
    private List<City> cities = new ArrayList<>();
}
