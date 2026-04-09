package com.example.photo_app.service;

import com.example.photo_app.dto.CommentRequest;
import com.example.photo_app.dto.CommentResponse;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public CommentResponse createComment(CommentRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Photo photo = photoRepository.findById(request.getPhotoId())
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .photo(photo)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return toResponse(savedComment);
    }

    public List<CommentResponse>getCommentsByPhoto(Long photoId){
        List<Comment> comments = commentRepository.findByPhotoId(photoId);

        return comments.stream()
                .map(this::toResponse)
                .toList();
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }

    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::toResponse)
                .toList();
    }

    private CommentResponse toResponse(Comment comment){
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
