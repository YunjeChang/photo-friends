package com.example.photo_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoRequest {

    private String imageUrl;
    private String caption;
    private Long userId;

}
