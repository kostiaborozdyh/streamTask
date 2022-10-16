package org.example.tasks;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.model.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.HashSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Tasks {

    @Getter
    @Setter
    @Data
    final static class ContinentCity{
        private final String continent;
        private final City city;
        public int getPopulation(){
            return city.getPopulation();
        }
    }

    @Getter
    @Setter
    @Data
    final static class DirectorsGenres{
        private final Integer idDirector;
        private final Integer numberGenres;
    }

    @Getter
    @Setter
    @Data
    final static class CountryCapitalCity{
        private final String countryName;
        private final City capitalCity;
    }

    //task1
    public static List<City> highestPopulatedCityOfEachCountry(List<Country> countryList) {
        return countryList.stream()
                .map(country -> country.getCities().stream().max(Comparator.comparing(City::getPopulation)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    //task2
    public static List<City> mostPopulatedCityOfEachContinent(List<Country> countryList) {
        Set<String> continentSet = continentSet(countryList);

        return countryList.stream()
                .map(country -> {
                    Optional<City> city = country.getCities().stream()
                            .max(Comparator.comparing(City::getPopulation));
                    return city.map(value -> new ContinentCity(country.getContinent(), value)).orElse(null);

                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(ContinentCity::getPopulation).reversed())
                .map(continentCity -> {
                    String continent = continentCity.getContinent();
                    if(continentSet.contains(continent)){
                        continentSet.remove(continent);
                        return continentCity.getCity();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(toList());
    }

    //task3
    public static Map<Integer, Integer> numberOfMoviesOfEachDirector(List<Director> directorList) {
        return directorList.stream()
                .collect(Collectors.toMap(Director::getId,x->x.getMovies().size()));
    }

    //task4
    public static Map<Integer, Integer> numberOfGenresOfEachDirectorsMovies(List<Director> directorList) {
      return directorList.stream()
               .map(director -> {
                   Set<Genre> genreSet = new HashSet<>();
                   director.getMovies().forEach(c->genreSet.addAll(c.getGenres()));
                   return new DirectorsGenres(director.getId(), genreSet.size());
               })
               .collect(Collectors.toMap(DirectorsGenres::getIdDirector,DirectorsGenres::getNumberGenres));
    }


    //task5
    public static City highestPopulatedCapitalCity(List<Country> countryList) {
        CountryCapitalCity countryCapitalCity = countryList.stream()
                .map(country -> new CountryCapitalCity(country.getName(), country.getCities().get(country.getCapital())))
                .max(Comparator.comparing(x->x.getCapitalCity().getPopulation()))
                .orElse(null) ;
        assert countryCapitalCity != null;
        return countryCapitalCity.getCapitalCity();
    }

    //task6
    public static List<City> highestPopulatedCapitalCityOfEachContinent(List<Country> countryList) {
        Set<String> continentSet = continentSet(countryList);
        return countryList.stream()
                .map(country -> new ContinentCity(country.getContinent(), country.getCities().get(country.getCapital())))
                .sorted(Comparator.comparingInt(ContinentCity::getPopulation).reversed())
                .map(continentCity -> {
                    String continent = continentCity.getContinent();
                    if(continentSet.contains(continent)){
                        continentSet.remove(continent);
                        return continentCity.getCity();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(toList());

    }

    //task7
    public static List<Country> SortingCountriesByNumberOfTheirCitiesInDescendingOrder(List<Country> countryList) {
        return countryList.stream()
                .filter(country -> !country.getCities().isEmpty())
                .sorted(Comparator.<Country, Integer>comparing(country -> country.getCities().size()).reversed())
                .collect(toList());
    }

    //task8
    public static List<Movie> MoviesWithDramaAndComedyOnly(List<Movie> movieList) {
        return movieList.stream()
                .filter(x -> x.getGenres().size() == 2)
                .filter(x -> x.getGenres().stream().map(Genre::getName).collect(toList()).contains("Drama"))
                .filter(x -> x.getGenres().stream().map(Genre::getName).collect(toList()).contains("Comedy"))
                .collect(toList());
    }


    //task9
    public static Map<Integer, List<Movie>> GroupingMoviesByYear(List<Movie> movieList) {
        return  movieList.stream()
                .collect(Collectors.groupingBy(Movie::getYear));
    }


    //task10
    public static List<Country> SortingCountriesByTheirPopulationDensities(List<Country> countryList) {
        return countryList.stream()
                .filter(x -> (x.getPopulation() / x.getSurfaceArea())!=0)
                .sorted(Comparator.<Country, Double> comparing(x -> (x.getPopulation() / x.getSurfaceArea())).reversed())
                .collect(toList());
    }

    private static Set<String> continentSet(List<Country> countryList){
        return  countryList.stream()
                .map(Country::getContinent)
                .collect(Collectors.toSet());
    }
}
