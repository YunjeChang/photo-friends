package com.example.photo_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "photo_likes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "photo_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

}
