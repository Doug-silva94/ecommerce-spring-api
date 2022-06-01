package com.dev.api.springrest.repositories;

import com.dev.api.springrest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "FROM User u WHERE u.username = ?1")
    Optional<User> findByLogin(String login);
}
