package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MovieRepository {

    Map<String,Movie> moviecollection = new HashMap<>();
    Map<String,Director> directorcollection = new HashMap<>();
    Map<String,String> pair = new HashMap<>();

    //Add a movie

    public MovieRepository() {
    }

    public String addMovie(Movie movie){
        moviecollection.put(movie.getName(),movie);
        return "success ";
    }
    //Add a director
    public String addDirector(Director director){
        directorcollection.put(director.getName(),director);
        return "success ";
    }
    //Pair an existing movie and director
    public String addMovieDirectorPair(String mname,String dname){
        pair.put(mname,dname);
        return "success ";
    }

    //Get Movie by movie name
    public Movie getMovieByName(String name){
        return moviecollection.get(name);
    }

    //Get Director by director name
    public Director getDirectorByName(String name){
        return directorcollection.get(name);
    }
    //Get List of movies name for a given
    public List<String> getMoviesByDirectorName(String dname){
        List<String> list = new ArrayList<>();
        for(String mname : pair.keySet()){
           if(pair.get(mname).equals(dname)){
               list.add(mname);
           }
        }
        return list;
    }

    //Get List of all movies added
    public List<Movie> findAllMovies(){
        List<Movie> list = new ArrayList<>();
        for(String name : moviecollection.keySet()){
            list.add(moviecollection.get(name));
        }
        return list;
    }

    //Delete a director and its movies from the records
    public String deleteDirectorByName(String dname){
        for(String mname : pair.keySet()){
            if(pair.get(mname).equals(dname)){
                pair.remove(mname);
            }
        }
        return "success ";
    }
    //Delete all directors and all movies by them from the records
    public String deleteAllDirectors(){
        directorcollection.clear();
        return "success ";
    }


}
