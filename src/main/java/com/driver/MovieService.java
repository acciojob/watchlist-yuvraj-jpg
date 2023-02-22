package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    void addMovie( Movie movie) {
        String movieName = movie.getName();
        if(!movieRepository.moviesSet.containsKey(movieName)) {
            movieRepository.addMovie(movieName,movie);
        }
    }

    void addDirector(Director director) {
        String directorName = director.getName();
        if(!movieRepository.directorsSet.containsKey(directorName)) {
            movieRepository.addDirector(directorName,director);
        }
    }

    void addMovieDirectorPair( String movieName, String directorName) {

        if(movieRepository.directorsSet.containsKey(directorName) && movieRepository.moviesSet.containsKey(movieName)) {

            Director director = movieRepository.directorsSet.get(directorName);
            Movie movie = movieRepository.moviesSet.get(movieName);

            if(movieRepository.pair.get(director) == null){
                List<Movie> listOfMovies = new ArrayList<>();
                listOfMovies.add(movie);
                director.setNumberOfMovies(director.getNumberOfMovies() +1);
                movieRepository.addMovieDirectorPair(director, listOfMovies);
            }
            else{
                List<Movie> listOfMovies = movieRepository.pair.get(director);
                for(Movie movies : listOfMovies){
                    if(movies.getName().equalsIgnoreCase(movieName)){
                        return;
                    }
                }
                director.setNumberOfMovies(director.getNumberOfMovies() +1);
                listOfMovies.add(movie);
                movieRepository.addMovieDirectorPair(director, listOfMovies);
            }

        }

    }

    Movie getMovieByName( String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }


    List<String> getMoviesByDirectorName(String directorName) {

        Director director = movieRepository.directorsSet.get(directorName);
        List<String> namesOfMovies = new ArrayList<>();

        if(!movieRepository.pair.containsKey(director)) {
            return namesOfMovies;
        }
        List<Movie> listOfMovies = movieRepository.getMoviesByDirectorName(director);

        for(Movie movie : listOfMovies) {
            namesOfMovies.add(movie.getName());
        }
        return namesOfMovies;
    }


    List<String> findAllMovies() {
        List<String> listOfMovies = new ArrayList<>();
        for(String movieName : movieRepository.moviesSet.keySet()) {
            listOfMovies.add(movieName);
        }
        return listOfMovies;
    }


    void deleteDirectorByName(String directorName) {

        Director director = movieRepository.directorsSet.get(directorName);

        movieRepository.directorsSet.remove(directorName);

        List <Movie> listOfMovies = movieRepository.pair.get(director);
        for( Movie movie : listOfMovies) {
            String movieName = movie.getName();
            movieRepository.moviesSet.remove(movieName);
        }

        movieRepository.pair.remove(director);

    }

    void deleteAllDirectors() {

        for(List <Movie>listOfMovies : movieRepository.pair.values()) {
            for(Movie movie : listOfMovies) {
                String movieName = movie.getName();
                movieRepository.moviesSet.remove(movieName);
            }
        }

        movieRepository.deleteAllDirectors();
    }

}