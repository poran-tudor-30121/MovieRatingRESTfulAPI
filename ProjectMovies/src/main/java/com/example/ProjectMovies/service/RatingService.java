package com.example.ProjectMovies.service;

import com.example.ProjectMovies.entity.Rating;
import com.example.ProjectMovies.repository.MovieRepository;
import com.example.ProjectMovies.repository.RatingsRepository;
import com.example.ProjectMovies.repository.UserCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final UserCrudRepository userCrudRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Rating> getAllRatings ()
    {
        List<Rating> allRatings = ratingsRepository.findAll();
        return allRatings;
    }
}
