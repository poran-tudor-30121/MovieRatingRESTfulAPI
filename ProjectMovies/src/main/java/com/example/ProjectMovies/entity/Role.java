package com.example.ProjectMovies.entity;

import com.example.ProjectMovies.enums.RoleValue;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROLE")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleValue roleValue;

}
