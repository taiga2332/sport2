package com.example.sport2.controller;

import jakarta.validation.Valid;
import com.example.sport2.dto.CreateUserRequest;
import com.example.sport2.dto.UserResponse;
import com.example.sport2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/create")
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
