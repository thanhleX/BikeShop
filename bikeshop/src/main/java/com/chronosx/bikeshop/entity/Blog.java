package com.chronosx.bikeshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blogs")
public class Blog implements UploadThumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sub_title", columnDefinition = "TEXT")
    private String subTitle;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image-url")
    private String thumbnailUrl;

    @Column(name = "thumbnail_public_id")
    private String thumbnailPublicId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "carousel_at")
    private LocalDateTime carouselAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_category_id", nullable = false)
    private BlogCategory blogCategory;
}
