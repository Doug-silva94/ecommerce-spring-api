package com.dev.api.springrest.controller;

import com.dev.api.springrest.dto.UserDto;
import com.dev.api.springrest.repository.UserRepository;
import com.dev.api.springrest.security.JwtUtil;
import com.dev.api.springrest.security.UserAuthenticationRequest;
import com.dev.api.springrest.security.UserAuthenticationResponse;
import com.dev.api.springrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuth(@RequestBody UserAuthenticationRequest user) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        }catch(Exception e){
            throw new Exception("Senha incorreta",e);

        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwt.generateToken(userDetails);
        return ResponseEntity.ok(new UserAuthenticationResponse(token));
    }
    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping
    public ResponseEntity<UserDto> find(@RequestParam String username) {
        return ResponseEntity.ok(userService.findById(Long.valueOf(username)));
    }

}
