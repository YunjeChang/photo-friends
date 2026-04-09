package com.example.photo_app.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhotoLikeResponse {

    private Long photoId;
    private Long likeCount;
    private boolean liked;

}
