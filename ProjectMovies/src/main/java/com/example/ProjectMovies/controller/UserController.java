package com.example.ProjectMovies.controller;

import com.example.ProjectMovies.DTO.*;
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

@Controller
@RequestMapping(path="/ProjectMovies")
public class UserController {

    @Autowired
    private  UserService userService;

    @CrossOrigin
    @GetMapping(path="/user")
    public @ResponseBody User showUser (@RequestBody UserUserDTO userUserDTO)
    {
        User user = userService.showUser(userUserDTO);
        return user;
    }
    @CrossOrigin
    @PostMapping(path="/addadmin") // Map ONLY POST Requests
    public @ResponseBody User addNewAdmin (@RequestBody UserDTO userDTO) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User user =userService.saveAdmin(userDTO);
        return user;
    }
    @CrossOrigin
    @PostMapping(path="/adduser") // Map ONLY POST Requests
    public @ResponseBody User addNewUser (@RequestBody UserDTO userDTO) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User user =userService.saveUser(userDTO);
        return user;
    }
    @CrossOrigin
    @PostMapping(path="/addmovie")
    public @ResponseBody Movie addNewMovie(@RequestBody MovieDTO movieDTO) throws AccessDeniedException {
        Movie movie = userService.addMovie(movieDTO);

        return movie;
    }
    @CrossOrigin
    @PostMapping(path="/addrating")
    public @ResponseBody Rating addNewRating(@RequestBody RatingDTO ratingDTO) throws AccessDeniedException
    {
        Rating rating = userService.addRating(ratingDTO);
        return rating;
    }

         @CrossOrigin
         @PostMapping(path="/login")
        public @ResponseBody String login(@RequestBody UserLoginDTO userLoginDTO)  throws AccessDeniedException
         {

             return userService.login(userLoginDTO);
         }


//        @GetMapping(path="/all")
//        public @ResponseBody Iterable<User> getAllUsers() {
//            // This returns a JSON or XML with the users
//            return userCrudRepository.findAll();
        }

