package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
   MovieService movieservice;


    //post
       // add movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieservice.addMovie(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }
       // add director
   @PostMapping("/add-director")
   public ResponseEntity<String> addDirector(@RequestBody Director director){
       movieservice.addDirector(director);
       return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
   }
   // put
    // Pair an existing movie and director
    @PutMapping("/add-movie-director-pair")
   public ResponseEntity<String> addMovieDirectorPair(@RequestParam String mname,@RequestParam String dname){
      movieservice.addMovieDirectorPair(mname,dname);
      return new ResponseEntity<>("Movie Director pair added successfully", HttpStatus.CREATED);
   }

    //get
    // get movie
        @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String mname){
        Movie x = null;
        x=movieservice.getMovieByName(mname);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
    // get director
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String dname){
        Director x= null;
        x=movieservice.getDirectorByName(dname);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
    //getMoviesByDirectorName
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String dname){
        List<String> x = new ArrayList<>();
        x=movieservice.getMoviesByDirectorName(dname);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
    //Get List of all movies added
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        List<Movie> x = new ArrayList<>();
        x=movieservice.findAllMovies();
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    //delete
    // Delete a director and its movies from the records
        @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String dname){
        movieservice.deleteDirectorByName(dname);
        return new ResponseEntity<>("Director and its movies are deleted from records successfully", HttpStatus.OK);
    }
    // Delete all directors and all movies by them from the records
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieservice.deleteAllDirectors();
        return new ResponseEntity<>("All Directors and their movies deleted", HttpStatus.OK);
    }


}
