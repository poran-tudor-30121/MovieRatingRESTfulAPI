package com.example.ProjectMovies.DTO;

import lombok.Data;

@Data
public class MovieDTO {
    private String title;
    private String director;

    private UserDTO movieAdder;
    //private double rating;
}
