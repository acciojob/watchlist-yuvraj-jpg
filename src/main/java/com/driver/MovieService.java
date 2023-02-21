package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
     void addMovie(Movie movie){
        String mname = movie.getName();
        if(!movieRepository.moviecollection.containsKey(mname)){
            movieRepository.addMovie(mname,movie);
        }
    }
     void addDirector(Director director){
        String dname = director.getName();
        if(!movieRepository.directorcollection.containsKey(dname)){
            movieRepository.addDirector(dname,director);
        }
    }
     void addMovieDirectorPair(String mname,String dname){
       if(movieRepository.directorcollection.containsKey(dname) && movieRepository.moviecollection.containsKey(mname)){
           Director director = movieRepository.directorcollection.get(dname);
           Movie movie =movieRepository.moviecollection.get(mname);
           if(movieRepository.pair.containsKey(director)){
               List<Movie> list = movieRepository.pair.get(director);
               list.add(movie);
               director.setNumberOfMovies(director.getNumberOfMovies()+1);
               movieRepository.addMovieDirectorPair(director,list);
           }
           else{
               List<Movie> list = new ArrayList<>();
               list.add(movie);
               director.setNumberOfMovies(director.getNumberOfMovies()+1);
               movieRepository.addMovieDirectorPair(director,list);
           }
       }
    }
     Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }
     Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }
     List<String> getMoviesByDirectorName(String dname){
         Director director = movieRepository.directorcollection.get(dname);
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
     List<Movie> findAllMovies(){
        List<Movie> list = new ArrayList<>();
        for(String name : movieRepository.moviecollection.keySet()){
            list.add(movieRepository.moviecollection.get(name));
        }
        return list;
    }
    void deleteDirectorByName(String directorName) {

        Director director = movieRepository.directorcollection.get(directorName);

        movieRepository.directorcollection.remove(directorName);

        List <Movie> listOfMovies = movieRepository.pair.get(director);
        for( Movie movie : listOfMovies) {
            String movieName = movie.getName();
            movieRepository.moviecollection.remove(movieName);
        }

        movieRepository.pair.remove(director);

    }

    public void deleteAllDirectors(){
         for(List<Movie> list : movieRepository.pair.values()){
             for(Movie movie : list){
                 movieRepository.moviecollection.remove(movie.getName());
             }
         }
         movieRepository.deleteAllDirectors();
    }
}
