//package com.dev.api.springrest.service;
//
//import com.dev.api.springrest.dto.UserDto;
//import com.dev.api.springrest.model.User;
//import com.dev.api.springrest.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserDto userToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUsername(user.getUsername());
//        userDto.setPassword(user.getPassword());
//
//        return userDto;
//    }
//
//    public User dtoToModel(UserDto userDto) {
//        User user = new User();
//        user.setUsername(userDto.getUsername());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//        return user;
//    }
//
//    public UserDto findById(Long id) {
//        return userRepository.findById(id)
//                .map(user -> userToDto(user))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//    public List<UserDto> findAll() {
//        return userRepository.findAll()
//                .stream()
//                .map(user -> userToDto(user))
//                .collect(Collectors.toList());
//    }
//
//    public Long save(UserDto userDto) {
//        User user = new User();
//        userToDto(user);
//        userRepository.save(user);
//        return user.getId();
//    }
//
//    public UserDto findByLogin(String username) {
//        return userRepository.findAll()
//                .stream()
//                .filter(user -> user.getId().equals(username))
//                .map(user -> userToDto(user))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//}
