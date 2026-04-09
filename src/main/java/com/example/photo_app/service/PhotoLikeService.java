package com.example.photo_app.service;

import com.example.photo_app.dto.PhotoLikeRequest;
import com.example.photo_app.dto.PhotoLikeResponse;
import com.example.photo_app.entity.Photo;
import com.example.photo_app.entity.PhotoLike;
import com.example.photo_app.entity.User;
import com.example.photo_app.repository.PhotoLikeRepository;
import com.example.photo_app.repository.PhotoRepository;
import com.example.photo_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoLikeService {

    private final PhotoLikeRepository photoLikeRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public PhotoLikeResponse likePhoto(PhotoLikeRequest request){
        if (photoLikeRepository.existsByUserIdAndPhotoId(request.getUserId(),request.getPhotoId())){
            throw new RuntimeException("You already liked this photo");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Photo photo = photoRepository.findById(request.getPhotoId())
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        PhotoLike photoLike = PhotoLike.builder()
                .user(user)
                .photo(photo)
                .build();

        photoLikeRepository.save(photoLike);

        return PhotoLikeResponse.builder()
                .photoId(photo.getId())
                .likeCount(photoLikeRepository.countByPhotoId(photo.getId()))
                .liked(true)
                .build();
    }

    public PhotoLikeResponse unlikePhoto(PhotoLikeRequest request){
        if(!photoLikeRepository.existsByUserIdAndPhotoId(request.getUserId(), request.getPhotoId())){
            throw new RuntimeException("Like not found");
        }

        photoLikeRepository.deleteByUserIdAndPhotoId(request.getUserId(), request.getPhotoId());

        return PhotoLikeResponse.builder()
                .photoId(request.getPhotoId())
                .likeCount(photoLikeRepository.countByPhotoId(request.getPhotoId()))
                .liked(false)
                .build();
    }

    public PhotoLikeResponse getLikeStatus(Long photoId, Long userId){
        boolean liked = photoLikeRepository.existsByUserIdAndPhotoId(userId, photoId);
        long likeCount = photoLikeRepository.countByPhotoId(photoId);

        return PhotoLikeResponse.builder()
                .photoId(photoId)
                .likeCount(likeCount)
                .liked(liked)
                .build();
    }

}
