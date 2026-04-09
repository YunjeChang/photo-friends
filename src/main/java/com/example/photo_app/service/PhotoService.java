package com.example.photo_app.service;

import com.example.photo_app.dto.PhotoRequest;
import com.example.photo_app.dto.PhotoResponse;
import com.example.photo_app.entity.Photo;
import com.example.photo_app.entity.User;
import com.example.photo_app.repository.PhotoRepository;
import com.example.photo_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public PhotoResponse createdPhoto(PhotoRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Photo photo = Photo.builder()
                .imageUrl(request.getImageUrl())
                .caption((request.getCaption()))
                .user(user)
                .build();

        Photo savedPhoto = photoRepository.save(photo);

        return toResponse(savedPhoto);
    }

    public List<PhotoResponse> getAllPhotos(){
        List<Photo> photos = photoRepository.findAll();

        return photos.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<PhotoResponse> getPhotosByUser(Long userId){
        List<Photo> photos = photoRepository.findByUserId(userId);

        return photos.stream()
                .map(this::toResponse)
                .toList();
    }

    public void deletePhoto(Long photoId){
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        photoRepository.delete(photo);
    }

    private PhotoResponse toResponse(Photo photo){
        return PhotoResponse.builder()
                .id(photo.getId())
                .imageUrl(photo.getImageUrl())
                .caption(photo.getCaption())
                .userId(photo.getUser().getId())
                .username(photo.getUser().getUsername())
                .createdAt(photo.getCreatedAt())
                .build();
    }

}
