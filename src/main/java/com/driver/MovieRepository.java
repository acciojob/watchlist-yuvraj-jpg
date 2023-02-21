package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MovieRepository {
    HashMap<String,Movie> moviecollection = new HashMap<>();
    HashMap<String,Director> directorcollection = new HashMap<>();
    HashMap<Director,List<Movie>> pair = new HashMap<>();
    //Add a movie
     void addMovie(String mname,Movie movie){
        moviecollection.put(mname,movie);
    }
    //Add a director
    void addDirector(String dname ,Director director){
        directorcollection.put(dname,director);
    }
    //Pair an existing movie and director
    void addMovieDirectorPair(Director director,List<Movie> movie){
         pair.put(director,movie);

    }
    //Get Movie by movie name
     Movie getMovieByName(String name){
            return moviecollection.get(name);
    }
    //Get Director by director name
     Director getDirectorByName(String name){
            return directorcollection.get(name);
    }
    //Get List of movies name for a given
     List<Movie> getMoviesByDirectorName(Director director){
            return pair.get(director);
    }
    //Get List of all movies added
    //Delete a director and its movies from the records
    //Delete all directors and all movies by them from the records
     void deleteAllDirectors(){
        directorcollection = new HashMap<>();
        pair = new HashMap<>();
    }


}
