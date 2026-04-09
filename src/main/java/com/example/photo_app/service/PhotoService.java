package com.example.photo_app.service;

import com.example.photo_app.dto.CommentResponse;
import com.example.photo_app.dto.PhotoDetailResponse;
import com.example.photo_app.dto.PhotoRequest;
import com.example.photo_app.dto.PhotoResponse;
import com.example.photo_app.entity.Comment;
import com.example.photo_app.entity.Photo;
import com.example.photo_app.entity.User;
import com.example.photo_app.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

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

    public PhotoDetailResponse getPhotoDetail(Long photoId){
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        List<CommentResponse> comments = commentRepository.findByPhotoId(photoId).stream()
                .map((this::toCommentResponse))
                .toList();

        return PhotoDetailResponse.builder()
                .id(photo.getId())
                .imageUrl(photo.getImageUrl())
                .caption(photo.getCaption())
                .userId(photo.getUser().getId())
                .username(photo.getUser().getUsername())
                .createdAt(photo.getCreatedAt())
                .comments(comments)
                .build();
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

    private CommentResponse toCommentResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .photoId(comment.getPhoto().getId())
                .createdAt(comment.getCreatedAt())
                .build();

    }

}
