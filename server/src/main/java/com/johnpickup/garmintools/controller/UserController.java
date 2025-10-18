package com.johnpickup.garmintools.controller;

import com.johnpickup.garmintools.data.User;
import com.johnpickup.garmintools.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
