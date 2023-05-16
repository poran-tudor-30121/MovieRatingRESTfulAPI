package com.example.ProjectMovies.repository;

import com.example.ProjectMovies.entity.Movie;
import com.example.ProjectMovies.entity.Rating;
import com.example.ProjectMovies.entity.RatingId;
import com.example.ProjectMovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, RatingId> {

    List<Rating> findByUser(User user);

    List<Rating> findByMovie(Movie movie);

    Rating findByUserAndMovie(User user, Movie movie);

    @Query("SELECT AVG(r.rating) FROM Rating r JOIN r.movie m WHERE m.title = :title")
    Double getAverageRatingByMovieTitle(@Param("title") String title);

}
