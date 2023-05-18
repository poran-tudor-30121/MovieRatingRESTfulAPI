package com.example.ProjectMovies.service;

import com.example.ProjectMovies.DTO.*;
import com.example.ProjectMovies.entity.Movie;
import com.example.ProjectMovies.entity.Rating;
import com.example.ProjectMovies.entity.Role;
import com.example.ProjectMovies.entity.User;
import com.example.ProjectMovies.enums.RoleValue;
import com.example.ProjectMovies.exceptions.MovieNotFoundException;
import com.example.ProjectMovies.exceptions.UserNotFoundException;
import com.example.ProjectMovies.repository.MovieRepository;
import com.example.ProjectMovies.repository.RatingsRepository;
import com.example.ProjectMovies.repository.UserCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCrudRepository userCrudRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    public User showUser(UserUserDTO userUserDTO)
    {
        User user = userCrudRepository.findByUsername(userUserDTO.getUsername());
        return user;
    }


    public String login(UserLoginDTO userLoginDTO)
    {
          User user = null;
          user = userCrudRepository.findByUsername(userLoginDTO.getUsername());
          if(user == null)
          {
              throw new IllegalArgumentException("Invalid username or password");
          }
          if(!user.getPassword().equals(userLoginDTO.getPassword()))
        {
            throw new IllegalArgumentException("Invalid username or password");
        }
          return "Logged in";

    }


    public User saveAdmin(UserDTO userDTO)
    {


       User user = mapUserDTOToAdmin(userDTO);
       userCrudRepository.save(user);
       return user;
    }
    public User saveUser (UserDTO userDTO) {
        User user = mapUserDTOToUser(userDTO);
        userCrudRepository.save(user);
        return user;
    }

    public Movie addMovie(MovieDTO movieDTO) throws AccessDeniedException {
        User admin = null;
        if (movieDTO.getMovieAdder() != null) {
            admin = userCrudRepository.findByUsername(movieDTO.getMovieAdder().getUserName());
        }
        if (admin == null) {
            throw new IllegalArgumentException("Invalid admin username");
        }
        List<Role> roles = admin.getRoles();
        boolean isAdmin = false;
        for (Role role : roles) {
            if (role.getRoleValue().equals(RoleValue.ADMIN)) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            throw new AccessDeniedException("Only admins can add movies.");
        }

        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setDirector(movieDTO.getDirector());
        movie = movieRepository.save(movie);
        return movie;
    }

    public Rating addRating(RatingDTO ratingDTO) throws AccessDeniedException {
        User user = null;
        Rating rating = new Rating();
        user = userCrudRepository.findByUsername(ratingDTO.getUser());
        if (user == null) {
            throw new IllegalArgumentException("Invalid user id");
        }
        Movie movie = null;
        movie = movieRepository.findByTitle(ratingDTO.getMovieTitle());
        if (movie == null) {
            throw new IllegalArgumentException("Invalid movie id");
        }

        Rating existingRating = ratingsRepository.findByUserAndMovie(user, movie);

        if (existingRating != null) {
            // If the user has already rated the movie, update their rating
            existingRating.setRating(ratingDTO.getRating());
            ratingsRepository.save(existingRating);
            rating = existingRating;
        } else {
            // If the user hasn't rated the movie, create a new rating

            rating.setUser(user);
            rating.setMovie(movie);
            rating.setRating(ratingDTO.getRating());

            ratingsRepository.save(rating);
        }

        // Update the movie's average rating
        double averageRating = ratingsRepository.getAverageRatingByMovieTitle(ratingDTO.getMovieTitle());
        movie.setRating(averageRating);
        movieRepository.save(movie);

        return rating;
    }


    private static User mapUserDTOToAdmin(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        Role userRole = new Role();
        userRole.setRoleValue(RoleValue.USER);
        userRole.getRoleValue().setRoleDescription(RoleValue.USER.getRoleDescription());

        Role adminRole = new Role();
        adminRole.setRoleValue(RoleValue.ADMIN);
        adminRole.getRoleValue().setRoleDescription(RoleValue.ADMIN.getRoleDescription());

        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        roles.add(adminRole);
        user.setRoles(roles);
        return user;
    }


    private static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        Role userRole = new Role();
        userRole.setRoleValue(RoleValue.USER);
        userRole.getRoleValue().setRoleDescription(RoleValue.USER.getRoleDescription());


        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);

        return user;
    }

}
