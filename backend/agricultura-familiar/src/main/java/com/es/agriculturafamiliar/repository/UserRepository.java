package com.es.agriculturafamiliar.repository;

import java.util.Optional;

import com.es.agriculturafamiliar.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    
}
