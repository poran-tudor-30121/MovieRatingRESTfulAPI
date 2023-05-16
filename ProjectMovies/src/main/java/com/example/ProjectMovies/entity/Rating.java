package com.example.ProjectMovies.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "RATING", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ID", "MOVIE_ID" }))

@IdClass(RatingId.class)

public class Rating {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;*/

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName="ID")
    @JsonManagedReference
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID",referencedColumnName = "ID")
    @JsonManagedReference
    private Movie movie;

    @Column(name = "RATING")
    private double rating;

    /*public Rating(User user,Movie movie,double rating)
    {
        this.user=user;
        this.movie=movie;
        this.rating=rating;
    }*/
}
