package com.dev.api.springrest.service;

import com.dev.api.springrest.model.User;
import com.dev.api.springrest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        if (user.isPresent()) {
            User userOnData = user.get();
            return new org.springframework.security.core.userdetails.User(userOnData.getUsername(), userOnData.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Usuario muito incorreto");
    }
}
