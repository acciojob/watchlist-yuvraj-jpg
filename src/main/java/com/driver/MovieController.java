package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody() Movie movie) {

        movieService.addMovie(movie);
        return new ResponseEntity<>("Your Movie has been added.", HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody() Director director) {

        movieService.addDirector(director);
        return new ResponseEntity<>("Name of director has been added.", HttpStatus.OK);
    }


    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName,
                                               @RequestParam("director") String directorName) {

        movieService.addMovieDirectorPair(movieName,directorName);

        return new ResponseEntity<>("Task Completed.", HttpStatus.OK);

    }


    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName) {

        Movie movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie ,HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName) {

        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director ,HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName) {

        List<String> listOfMovies = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(listOfMovies ,HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies() {

        List<String> listOfMovies = movieService.findAllMovies();
        return new ResponseEntity<>(listOfMovies ,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String directorName) {

        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Task Completed." ,HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors() {

        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Task Completed." ,HttpStatus.OK);
    }

}