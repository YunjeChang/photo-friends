package com.example.photo_app.repository;

import com.example.photo_app.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByUserId(Long id);

}
