package com.chronosx.bikeshop.service;

import java.io.IOException;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewBlogRequest;
import com.chronosx.bikeshop.dto.request.UpdateBlogRequest;
import com.chronosx.bikeshop.dto.response.BlogResponse;

public interface BlogService {

    Page<BlogResponse> findAllBlogsWithPaginationByBlogCategoryId(Long blockCategoryId, int page, int size);

    BlogResponse findBlogById(Long id);

    Page<BlogResponse> findAllCarouselBlog(int page, int size);

    BlogResponse addNewBlog(CreateNewBlogRequest request) throws IOException;

    BlogResponse changeCarousel(Long id);

    Page<BlogResponse> findAllBlogs(int page, int size);

    BlogResponse updateBlogById(Long blogId, UpdateBlogRequest request) throws IOException;

    void deleteBlogById(Long blogId);
}
