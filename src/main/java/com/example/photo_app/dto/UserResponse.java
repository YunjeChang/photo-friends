package com.example.photo_app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String profileImageUrl;

}
