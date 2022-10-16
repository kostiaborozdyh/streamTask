package org.example.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Movie {
    private int id;
    private String title;
    private int year;
    private String imdb;
    private List<Genre> genres = new ArrayList<>();
    private List<Director> directors = new ArrayList<>();
}