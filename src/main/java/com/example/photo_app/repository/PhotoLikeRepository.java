package com.example.photo_app.repository;

import com.example.photo_app.entity.PhotoLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoLikeRepository extends JpaRepository<PhotoLike, Long> {

    boolean existsByUserIdAndPhotoId(Long userId, long photoId);

    long countByPhotoId(Long photoId);

    void deleteByUserIdAndPhotoId(Long userId, Long photoId);
}
