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
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class MovieService {
    private final UserCrudRepository userCrudRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMoviesByTitle(String titlePref) {
        List<Movie> matchingMovies = new ArrayList<>();
        int numOfMovies = 0;

        // Prepare the search pattern with case-insensitive flag and ignoring the order of characters
        Pattern pattern = Pattern.compile(".*" + titlePref + ".*", Pattern.CASE_INSENSITIVE);

        // Query the database for movies
        List<Movie> allMovies = movieRepository.findAll();

        for (Movie movie : allMovies) {
            String movieTitle = movie.getTitle();
            if (pattern.matcher(movieTitle).matches()) {
                matchingMovies.add(movie);
                numOfMovies++;
                System.out.println("Found matching movie: " + movie.getTitle());
            } else {
                System.out.println(movie.getTitle() + " doesn't match the search criteria: " + titlePref);
            }
        }

        System.out.println("Number of matching movies: " + numOfMovies);
        System.out.println("Total movies in the repository: " + allMovies.size());

        return matchingMovies;
    }
    public List<Movie> getMoviesByYear(int year) {
        List<Movie> matchingMovies = new ArrayList<>();

        // query the database for movies released in the given year
        List<Movie> allMovies = movieRepository.findAll(); // Assuming you have a movieRepository object

        for (Movie movie : allMovies) {
            if (movie.getYear() == year) {
                matchingMovies.add(movie);
            }
        }

        return matchingMovies;
    }

    public List<Movie> getMoviesByDirector(String directorPref) {
        List<Movie> matchingMovies = new ArrayList<>();

        // query the database for movies released in the given year
        Pattern pattern = Pattern.compile(".*" + directorPref + ".*", Pattern.CASE_INSENSITIVE);
        List<Movie> allMovies = movieRepository.findAll(); // Assuming you have a movieRepository object

        for (Movie movie : allMovies) {
            String movieDirector = movie.getDirector();
            if (pattern.matcher(movieDirector).matches()) {
                matchingMovies.add(movie);
            }
        }

        return matchingMovies;
    }

    public List<Movie> getMoviesByGenre(String genrePref) {
        List<Movie> matchingMovies = new ArrayList<>();

        // query the database for movies released in the given year
        List<Movie> allMovies = movieRepository.findAll(); // Assuming you have a movieRepository object
        Pattern pattern = Pattern.compile(".*" + genrePref + ".*", Pattern.CASE_INSENSITIVE);


        for (Movie movie : allMovies) {
            String movieGenre = movie.getGenre();
            if (pattern.matcher(movieGenre).matches()) {
                matchingMovies.add(movie);
            }
        }

        return matchingMovies;
    }
    public List<Movie> getAllMovies()
    {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }
}
