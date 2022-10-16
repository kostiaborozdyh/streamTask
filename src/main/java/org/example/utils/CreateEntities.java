package org.example.utils;

import org.example.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateEntities {


    private static List<City> createListRandomCity(String countyCode) {
        List<City> cityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            City randomCity = new City();
            randomCity.setId(i);
            randomCity.setName("City№" + i);
            int population = (int) (Math.random() * 10000);
            randomCity.setPopulation(population);
            randomCity.setCountryCode(countyCode);
            cityList.add(randomCity);
        }
        return cityList;
    }

    public static List<Country> createListRandomCountry() {
        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Country randomCountry = new Country();
            randomCountry.setCode("CC№" + i);
            randomCountry.setName("Country№" + i);
            String continent = createRandomContinent();
            randomCountry.setContinent(continent);
            randomCountry.setSurfaceArea(Math.random() * 600000);
            List<City> cityList = createListRandomCity("CC№" + i);
            randomCountry.setPopulation((int) (Math.random() * 100000000));
            randomCountry.setGnp(Math.random() * 500000 + 50000);
            randomCountry.setCapital((int) (Math.random() * 4));
            randomCountry.setCities(cityList);
            countryList.add(randomCountry);
        }
        return countryList;
    }

    private static String createRandomContinent() {
        int randomId = (int) (Math.random() * 4);
        switch (randomId) {
            case 0:
                return "Continent0";
            case 1:
                return "Continent1";
            case 2:
                return "Continent2";
            case 3:
                return "Continent3";
            default:
                return "Continent4";
        }
    }
    public static List<Movie> createListMovie(int countMovie){
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < countMovie; i++) {
            Movie movie = new Movie();
            movie.setImdb("imdb"+i);
            movie.setId(i);
            movie.setTitle("Movie"+i);
            movie.setYear((int) (Math.random() * 21)+2000);
            movie.setGenres(createListGenre());
            movie.setDirectors(createListDirectorForMovie());
            movieList.add(movie);
        }
        return movieList;
    }
    public static List<Director> createListDirector(){
        List<Director> directorList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Director director = new Director();
            director.setId(i);
            director.setImdb("imdb"+i);
            director.setName("Director"+i);
            int countMovie = (int) (Math.random()*5)+1;
            director.setMovies(createListMovie(countMovie));
            directorList.add(director);
        }
        return directorList;
    }


    private static List<Genre> createListGenre() {
        Set<Genre> genreSet = new HashSet<>();
        int countGenre = (int) (Math.random() * 12)+1;
        for (int i = 0; i < countGenre; i++) {
            int idGenre = (int) (Math.random() * 12);
            genreSet.add(createGenre(idGenre));
        }
        return new ArrayList<>(genreSet);
    }

    private static List<Director> createListDirectorForMovie() {
        int countDirector = (int) (Math.random() * 5)+1;
        List<Director> directorList = new ArrayList<>();
        for (int i = 0; i < countDirector; i++) {
            Director director = new Director();
            director.setId(i);
            director.setImdb("imdb"+i);
            director.setName("Director"+i);
            directorList.add(director);
        }
        return directorList;
    }

    private static Genre createGenre(int id) {
        switch (id) {
            case 0:
                return new Genre(0, "Drama");
            case 1:
                return new Genre(1, "Action");
            case 2:
                return new Genre(2, "Adventure");
            case 3:
                return new Genre(3, "Animated");
            case 4:
                return new Genre(4, "Comedy");
            case 5:
                return new Genre(5, "Fantasy");
            case 6:
                return new Genre(6, "Historical");
            case 7:
                return new Genre(7, "Horror");
            case 8:
                return new Genre(8, "Noir");
            case 9:
                return new Genre(9, "Science");
            case 10:
                return new Genre(10, "Thriller");
            default:
                return new Genre(11, "Western");
        }
    }
}
