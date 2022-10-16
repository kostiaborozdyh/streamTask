package org.example.main;


import org.example.model.*;
import org.example.tasks.Tasks;
import org.example.utils.CreateEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Country> countryList = CreateEntities.createListRandomCountry();
        System.out.println("Country List");
        for (Country country : countryList) {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.print("Country code:" + country.getCode());
            System.out.print(" Country name:" + country.getName());
            System.out.println(" Country continent:" + country.getContinent());
            System.out.print("Country surfaceArea:" + country.getSurfaceArea());
            System.out.println(" Country population:" + country.getPopulation());
            System.out.print("Country gnp:" + country.getGnp());
            System.out.println(" Country capital:" + country.getCapital());
            System.out.println("Country Cities:");
            List<City> cityList = country.getCities();
            for (City city : cityList) {
                System.out.print("id:" + city.getId() + " population:" + city.getPopulation());
                System.out.println(" name:" + city.getName() + " country code:" + city.getCountryCode());
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Solution Tasks");
        System.out.println("1. Find the highest populated city of each country");
        List<City> task1 = Tasks.highestPopulatedCityOfEachCountry(countryList);
        printListCity(task1);
        System.out.println("2. Find the most populated city of each continent");
        List<City> task2 = Tasks.mostPopulatedCityOfEachContinent(countryList);
        printListCity(task2);
        System.out.println("5. Find the highest populated capital city");
        City task5 = Tasks.highestPopulatedCapitalCity(countryList);
        printCity(task5);
        System.out.println("6. Find the highest populated capital city of each continent");
        List<City> task6 = Tasks.highestPopulatedCapitalCityOfEachContinent(countryList);
        printListCity(task6);
        System.out.println("7. Sort the countries by number of their cities in descending order");
        List<Country> task7 = Tasks.SortingCountriesByNumberOfTheirCitiesInDescendingOrder(countryList);
        printListCountry(task7);
        System.out.println("10. Sort the countries by their population densities in descending order ignoring zero population countries");
        List<Country> task10 = Tasks.SortingCountriesByTheirPopulationDensities(countryList);
        printListCountry(task10);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Director List");
        List<Director> directorList = CreateEntities.createListDirector();
        for (Director director : directorList) {
            printDirector(director);
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Solution Tasks");
        System.out.println("3. Find the number of movies of each director");
        Map<Integer, Integer> task3 = Tasks.numberOfMoviesOfEachDirector(directorList);
        for (Map.Entry<Integer, Integer> map : task3.entrySet()) {
            System.out.println("directorId:" + map.getKey() + " number of movies:" + map.getValue());
        }
        System.out.println("4. Find the number of genres of each director's movies");
        Map<Integer, Integer> task4 = Tasks.numberOfGenresOfEachDirectorsMovies(directorList);
        for (Map.Entry<Integer, Integer> map : task4.entrySet()) {
            System.out.println("directorId:" + map.getKey() + " number of genres:" + map.getValue());
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Movie List:");
        List<Movie> movieList = CreateEntities.createListMovie(5);
        movieList.add(createComedyDramaMovie());
        printMovieList(movieList);
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("Solution Tasks");
        System.out.println("8. Find the list of movies having the genres Drama and Comedy only");
        List<Movie> task8 = Tasks.MoviesWithDramaAndComedyOnly(movieList);
        printMovieListWithId(task8);
        System.out.println();
        System.out.println("9. Group the movies by the year and list them");
        Map<Integer, List<Movie>> task9 = Tasks.GroupingMoviesByYear(movieList);
        for (Map.Entry<Integer, List<Movie>> map : task9.entrySet()) {
            System.out.print("year:" + map.getKey() + " movies ");
            printMovieListWithId(map.getValue());
            System.out.println();
        }
    }

    private static void printListCity(List<City> cityList) {
        for (City city : cityList) {
            printCity(city);
        }
    }

    private static void printCity(City city) {
        System.out.print("id:" + city.getId() + " name:" + city.getName());
        System.out.println(" country code:" + city.getCountryCode());
    }

    private static void printDirector(Director director) {
        System.out.print("id:" + director.getId() + " name:" + director.getName());
        System.out.println(" imdb:" + director.getImdb());
        System.out.println("MovieList:");
        printMovieList(director.getMovies());
    }

    private static void printMovieList(List<Movie> movieList) {
        for (Movie movie : movieList) {
            System.out.print("id:" + movie.getId() + " title:" + movie.getTitle());
            System.out.println(" year:" + movie.getYear() + " imdb:" + movie.getImdb());
            for (Genre genre : movie.getGenres()) {
                System.out.print(genre.getName() + " ");
            }
            System.out.println();
        }
    }

    private static void printMovieListWithId(List<Movie> movieList) {
        for (Movie movie : movieList) {
            System.out.print("id:" + movie.getId() + " ");
        }
    }

    private static void printListCountry(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.print("CountryCode:" + country.getCode() + " ");
        }
        System.out.println();
    }

    private static Movie createComedyDramaMovie() {
        Movie movieDramaComedy = new Movie();
        movieDramaComedy.setYear(2012);
        movieDramaComedy.setGenres(createComedyDrama());
        movieDramaComedy.setId(5);
        movieDramaComedy.setTitle("DramaComedy");
        movieDramaComedy.setImdb("Imdb5");
        return movieDramaComedy;
    }

    private static List<Genre> createComedyDrama() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(0, "Comedy"));
        genreList.add(new Genre(1, "Drama"));
        return genreList;
    }
}
