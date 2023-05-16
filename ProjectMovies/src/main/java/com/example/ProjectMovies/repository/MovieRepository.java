package com.example.ProjectMovies.repository;

import com.example.ProjectMovies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie,Integer> {

    Movie findByTitle(String title);

}
