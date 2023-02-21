package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }
    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }
    public String addMovieDirectorPair(String mname,String dname){
        return movieRepository.addMovieDirectorPair(mname,dname);
    }
    public Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }
    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }
    public List<String> getMoviesByDirectorName(String director){
        return movieRepository.getMoviesByDirectorName(director);
    }
    public List<Movie> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public String deleteDirectorByName(String dname){
        return movieRepository.deleteDirectorByName(dname);
    }
    public String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }
}
