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
    Map<String,List<String>> pair = new HashMap<>();
    //Add a movie
    public void addMovie(Movie movie){
        moviecollection.put(movie.getName(),movie);
    }
    //Add a director
    public void addDirector(Director director){
        directorcollection.put(director.getName(),director);
    }
    //Pair an existing movie and director
    public void addMovieDirectorPair(String mname,String dname){
        List<String> list = new ArrayList<>();
        if(pair.containsKey(dname)){
            list = pair.get(dname);
            list.add(mname);
            pair.put(dname,list);
        }
        else{
            list.add(mname);
            pair.put(dname,list);
        }
    }

    //Get Movie by movie name
    public Movie getMovieByName(String name){
        if(moviecollection.containsKey(name)){
            return moviecollection.get(name);
        }
        else{
            return null;
        }

    }

    //Get Director by director name
    public Director getDirectorByName(String name){
        if(directorcollection.containsKey(name)){
            return directorcollection.get(name);
        }
        else{
            return null;
        }

    }
    //Get List of movies name for a given
    public List<String> getMoviesByDirectorName(String dname){
        if(pair.containsKey(dname)){
            return pair.get(dname);
        }
        else{
            return null;
        }
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
    public void deleteDirectorByName(String dname){
        if(directorcollection.containsKey(dname)){
            directorcollection.remove(dname);
        }
    }
    //Delete all directors and all movies by them from the records
    public void deleteAllDirectors(){
        directorcollection.clear();
        moviecollection.clear();
    }


}
