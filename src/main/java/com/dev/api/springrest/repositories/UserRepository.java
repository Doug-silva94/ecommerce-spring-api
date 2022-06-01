package com.dev.api.springrest.repositories;

import com.dev.api.springrest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
