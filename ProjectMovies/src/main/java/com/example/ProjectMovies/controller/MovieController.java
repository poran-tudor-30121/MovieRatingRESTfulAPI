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

    @CrossOrigin
    @PostMapping(path="/searchmovie") // Map ONLY POST Requests
    public @ResponseBody List<Movie> searchMovie(@RequestBody Map<String, String> request) {
        String searchedMovie = request.get("title");
        return movieService.searchMovie(searchedMovie);
    }

}
