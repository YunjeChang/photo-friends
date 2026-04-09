package com.example.photo_app.controller;

import com.example.photo_app.dto.CommentRequest;
import com.example.photo_app.entity.Comment;
import com.example.photo_app.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment createComment(@RequestBody CommentRequest request){
        return commentService.createComment(request);
    }

    @GetMapping("/photo/{photoId}")
    public List<Comment> getCommentsByPhoto(@PathVariable Long photoId){
        return commentService.getCommentsByPhoto(photoId);
    }

    @GetMapping("/photo")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "Comment deleted successfully";
    }

}
