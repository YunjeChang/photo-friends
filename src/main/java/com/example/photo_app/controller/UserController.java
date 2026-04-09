package com.example.photo_app.controller;

import com.example.photo_app.dto.UserResponse;
import com.example.photo_app.dto.UserSignupRequest;
import com.example.photo_app.entity.User;
import com.example.photo_app.repository.UserRepository;
import com.example.photo_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }




}
