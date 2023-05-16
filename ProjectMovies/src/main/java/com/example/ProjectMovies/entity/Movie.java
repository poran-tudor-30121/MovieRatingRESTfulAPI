package com.example.ProjectMovies.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

import java.util.List;

@Entity
@Data
@Table(name = "MOVIE")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",unique = true, nullable = false)
    private Integer id;

    @Column(name = "TITLE",unique = true,nullable = false)
    private String title;

    @Column(name = "DIRECTOR",nullable = false)
    private String director;

    @Column(name = "RATING")
    private Double rating;

    @Column(name ="GENRE")
    private String genre;

    @Column(name = "OVERVIEW", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "YEAR")
    private Integer year;



    @Transient
    private double totalRating;
    @Transient
    private int numOfRatings;

   @OneToMany(fetch = FetchType.LAZY,mappedBy="movie")
   @JsonBackReference
    private List<Rating> ratingList;

}
