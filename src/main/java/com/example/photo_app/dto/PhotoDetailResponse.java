package com.example.photo_app.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PhotoDetailResponse {

    private Long id;
    private String imageUrl;
    private String caption;
    private Long userId;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentResponse> comments;

}
