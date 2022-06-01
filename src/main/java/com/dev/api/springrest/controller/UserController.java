/*package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.UserDto;
import com.dev.api.springrest.repositories.UserRepository;
import com.dev.api.springrest.security.JwtUtil;
import com.dev.api.springrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwt;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping
    public ResponseEntity<UserDto> find (@RequestParam String username){
        return ResponseEntity.ok(userService.findById(Long.valueOf(username)));
    }

}
*/