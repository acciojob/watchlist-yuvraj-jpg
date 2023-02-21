package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
   MovieService movieservice;

    public MovieController() {
    }

    //post
       // add movie
    @PostMapping("/movies/add-movie")
    public String addMovie(@RequestBody Movie movie){
        return movieservice.addMovie(movie);
    }
       // add director
   @PostMapping("/movies/add-director")
   public String addDirector(@RequestBody Director director){
       return movieservice.addDirector(director);
   }
   // put
    // Pair an existing movie and director
    @PutMapping("/movies/add-movie-director-pair")
   public String addMovieDirectorPair(@RequestParam("mname") String mname,@RequestParam("dname") String dname){
      return movieservice.addMovieDirectorPair(mname,dname);
   }

    //get
    // get movie
        @GetMapping("/movies/get-movie-by-name/{name}")
    public Movie getMovieByName(@PathVariable("name") String name){
        return movieservice.getMovieByName(name);
    }
    // get director
    @GetMapping("/movies/get-director-by-name/{name}")
    public Director getDirectorByName(@PathVariable("name") String name){
        return movieservice.getDirectorByName(name);
    }
    //getMoviesByDirectorName
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public List<String> getMoviesByDirectorName(@PathVariable("director") String director){
        return movieservice.getMoviesByDirectorName(director);
    }
    //Get List of all movies added
    @GetMapping("/movies/get-all-movies")
    public List<Movie> findAllMovies(){
        return movieservice.findAllMovies();
    }

    //delete
    // Delete a director and its movies from the records
    @DeleteMapping("/movies/delete-director-by-name")
    public String deleteDirectorByName(@RequestParam("dname") String dname){
        return movieservice.deleteDirectorByName(dname);
    }
    // Delete all directors and all movies by them from the records
    @DeleteMapping("/movies/delete-all-directors")
    public String deleteAllDirectors(){
        return movieservice.deleteAllDirectors();
    }


}
