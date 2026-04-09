package com.example.photo_app.controller;

import com.example.photo_app.dto.PhotoDetailResponse;
import com.example.photo_app.dto.PhotoRequest;
import com.example.photo_app.dto.PhotoResponse;
import com.example.photo_app.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping
    public PhotoResponse createPhoto(@RequestBody PhotoRequest request){
        return photoService.createdPhoto(request);
    }

    @GetMapping
    public List<PhotoResponse> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{photoId}")
    public PhotoDetailResponse getPhotoDetail(@PathVariable Long photoId){
        return photoService.getPhotoDetail(photoId);
    }

    @GetMapping("/user/{userId}")
    public List<PhotoResponse> getUserPhotos(@PathVariable Long userId){
        return photoService.getPhotosByUser(userId);
    }

    @DeleteMapping("/{photoId}")
    public String deletePhoto(@PathVariable Long photoId){
        photoService.deletePhoto(photoId);
        return "Photo deleted successfully";
    }


}
