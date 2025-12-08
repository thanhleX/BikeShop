package com.chronosx.bikeshop.repository;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("select a from Blog a where a.blogCategory.id = ?1 and a.isActive = true")
    Page<Blog> findAllBlogsByBlogCategoryIdWithPagination(Long blockCategoryId, Pageable pageable);

    @Query("select a from Blog a where a.isActive = true")
    Page<Blog> findAllBlogsWithPagination(Pageable pageable);

    @Query("select a from Blog a where a.carouselAt IS NOT NULL and a.isActive = true")
    Page<Blog> findAllCarouselBlog(Pageable pageable);

    @Query("select a from Blog a where a.id = ?1 and a.isActive = true")
    @NotNull
    Optional<Blog> findById(@NotNull Long id);
}
