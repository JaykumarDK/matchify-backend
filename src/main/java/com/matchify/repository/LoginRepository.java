package com.matchify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchify.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    // Used for login (email + password)
    Login findByEmailAndPassword(String email, String password);

    // Optional: useful later
    Login findByEmail(String email);
}
