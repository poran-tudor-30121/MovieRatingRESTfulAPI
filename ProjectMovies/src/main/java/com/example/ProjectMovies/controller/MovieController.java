package com.example.ProjectMovies.controller;
import com.example.ProjectMovies.DTO.MovieDTO;
import com.example.ProjectMovies.DTO.RatingDTO;
import com.example.ProjectMovies.DTO.UserDTO;
import com.example.ProjectMovies.DTO.UserLoginDTO;
import com.example.ProjectMovies.entity.Movie;
import com.example.ProjectMovies.entity.Rating;
import com.example.ProjectMovies.entity.User;
import com.example.ProjectMovies.repository.UserCrudRepository;
import com.example.ProjectMovies.service.MovieService;
import com.example.ProjectMovies.service.RatingService;
import com.example.ProjectMovies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/ProjectMovies")
public class MovieController {



    @Autowired
    private MovieService movieService;
    @Autowired
    private RatingService ratingService;

    @CrossOrigin
    @GetMapping(path="/userRatings")
    public @ResponseBody List<Rating> getAllRatings()
    {
        return ratingService.getAllRatings();
    }
    @CrossOrigin
    @GetMapping(path = "/moviesByYear/{year}")
    public @ResponseBody List<Movie> getMoviesByYear(@PathVariable int year) {
        return movieService.getMoviesByYear(year);
    }

    @CrossOrigin
    @PostMapping(path="/moviesByTitle") // Map ONLY POST Requests
    public @ResponseBody List<Movie> searchMovie(@RequestBody Map<String, String> request) {
        String searchedMovie = request.get("title");
        return movieService.getMoviesByTitle(searchedMovie);
    }



    @CrossOrigin
    @PostMapping(path = "/moviesByDirector")
    public @ResponseBody List<Movie> getMoviesByDirector(@RequestBody Map<String,String> request) {
        String searchedDirector = request.get("director");
        return movieService.getMoviesByDirector(searchedDirector);
    }
    @CrossOrigin
    @PostMapping(path = "/moviesByGenre")
    public @ResponseBody List<Movie> getMoviesByYear(@RequestBody Map<String,String> request) {

        String searchedGenre = request.get("genre");
        return movieService.getMoviesByGenre(searchedGenre);
    }
    @CrossOrigin
    @PostMapping(path = "/movies")
    public @ResponseBody List<Movie> getAllMovies() {

        return movieService.getAllMovies();
    }


}
