package com.example.photo_app.controller;

import com.example.photo_app.dto.PhotoLikeRequest;
import com.example.photo_app.dto.PhotoLikeResponse;
import com.example.photo_app.service.PhotoLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final PhotoLikeService photoLikeService;

    @PostMapping
    public PhotoLikeResponse likePhoto(@RequestBody PhotoLikeRequest request){
        return photoLikeService.likePhoto(request);
    }

    @DeleteMapping
    public PhotoLikeResponse unlikePhoto(@RequestBody PhotoLikeRequest request){
        return photoLikeService.unlikePhoto(request);
    }

    @GetMapping("/photo/{photoId}/user/{userId}")
    public PhotoLikeResponse getLikesStatus(@PathVariable Long photoId, @PathVariable Long userId){
        return photoLikeService.getLikeStatus(photoId, userId);
    }

}
