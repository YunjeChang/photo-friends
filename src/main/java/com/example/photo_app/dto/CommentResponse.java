package com.example.photo_app.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private Long userId;
    private String username;
    private Long photoId;
    private LocalDateTime createdAt;
}
