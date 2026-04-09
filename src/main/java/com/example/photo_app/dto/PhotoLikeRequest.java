package com.example.photo_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoLikeRequest {

    private Long userId;
    private Long photoId;

}
