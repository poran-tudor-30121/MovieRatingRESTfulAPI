package com.example.ProjectMovies.repository;

import com.example.ProjectMovies.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends CrudRepository <User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);
}
