package com.example.ProjectMovies.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private Integer id;

    @Column(name = "USER_NAME",unique = true,nullable = false)
    private String username;
    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;
    @Column(name = "PASSWORD",nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user")
    @JsonBackReference
    private List<Rating> userRatings;

}
