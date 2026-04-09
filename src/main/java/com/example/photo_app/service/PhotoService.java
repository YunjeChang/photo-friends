package com.example.photo_app.service;

import com.example.photo_app.dto.PhotoRequest;
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

    public Photo createdPhoto(PhotoRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Photo photo = Photo.builder()
                .imageUrl(request.getImageUrl())
                .caption((request.getCaption()))
                .user(user)
                .build();

        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }

    public List<Photo> getPhotosByUser(Long userId){
        return photoRepository.findByUserId(userId);
    }

    public void deletePhoto(Long photoId){
        photoRepository.deleteById(photoId);
    }

}
