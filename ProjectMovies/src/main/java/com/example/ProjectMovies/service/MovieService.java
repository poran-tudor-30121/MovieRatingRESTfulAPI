package com.example.ProjectMovies.service;

import com.example.ProjectMovies.repository.MovieRepository;
import com.example.ProjectMovies.repository.RatingsRepository;
import com.example.ProjectMovies.repository.UserCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ProjectMovies.DTO.MovieDTO;
import com.example.ProjectMovies.DTO.RatingDTO;
import com.example.ProjectMovies.DTO.UserDTO;
import com.example.ProjectMovies.DTO.UserLoginDTO;
import com.example.ProjectMovies.entity.Movie;
import com.example.ProjectMovies.entity.Rating;
import com.example.ProjectMovies.entity.Role;
import com.example.ProjectMovies.entity.User;
import com.example.ProjectMovies.enums.RoleValue;
import com.example.ProjectMovies.exceptions.MovieNotFoundException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieService {
    private final UserCrudRepository userCrudRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> searchMovie(String searchedMovie) {
        List<Movie> matchingMovies = new ArrayList<>();
        int numOfMovies = 0;

        // query the database for movies whose titles start with the given string
        List<Movie> allMovies = movieRepository.findAll();

        for (Movie movie : allMovies) {
            if (movie.getTitle().startsWith(searchedMovie)){
                matchingMovies.add(movie);
                numOfMovies++;
                System.out.println("Found matching movie: " + movie.getTitle());
            }
            else
                System.out.println(movie.getTitle()+" Doesnt start with " + searchedMovie);
        }

        System.out.println("Number of matching movies: " + numOfMovies);
        System.out.println("Total movies in the repository:" + allMovies.size());

        return matchingMovies;
    }
}
