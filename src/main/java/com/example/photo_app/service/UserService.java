package com.example.photo_app.service;

import com.example.photo_app.dto.UserResponse;
import com.example.photo_app.dto.UserSignupRequest;
import com.example.photo_app.entity.User;
import com.example.photo_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse signup(UserSignupRequest request){

        userRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("Username already exists");
                });

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("email already exists");
                });

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);

    }

    private UserResponse toResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

}
