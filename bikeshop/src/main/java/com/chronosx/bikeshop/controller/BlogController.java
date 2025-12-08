package com.chronosx.bikeshop.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewBlogRequest;
import com.chronosx.bikeshop.dto.request.UpdateBlogRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.BlogResponse;
import com.chronosx.bikeshop.service.BlogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ApiResponse<Page<BlogResponse>> findAllBlogsByBlogCategoryIdWithPagination(
            @RequestParam Long blogCategoryId, @RequestParam int page, @RequestParam int size) {
        return ApiResponse.<Page<BlogResponse>>builder()
                .result(blogService.findAllBlogsWithPaginationByBlogCategoryId(blogCategoryId, page, size))
                .build();
    }

    @GetMapping("all")
    public ApiResponse<Page<BlogResponse>> findAllBlogs(@RequestParam int page, int size) {
        return ApiResponse.<Page<BlogResponse>>builder()
                .result(blogService.findAllBlogs(page, size))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BlogResponse> findBlogById(@PathVariable Long id) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.findBlogById(id))
                .build();
    }

    @GetMapping("carousel")
    public ApiResponse<Page<BlogResponse>> findAllCarousel(@RequestParam int page, @RequestParam int size) {
        return ApiResponse.<Page<BlogResponse>>builder()
                .result(blogService.findAllCarouselBlog(page, size))
                .build();
    }

    @PostMapping
    public ApiResponse<BlogResponse> addNewBlog(@ModelAttribute CreateNewBlogRequest request) throws IOException {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.addNewBlog(request))
                .build();
    }

    @PutMapping("carousel/{id}")
    public ApiResponse<BlogResponse> setNewCarousel(@PathVariable Long id) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.changeCarousel(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BlogResponse> updateBlogById(@PathVariable Long id, @ModelAttribute UpdateBlogRequest request)
            throws IOException {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.updateBlogById(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBlogById(@PathVariable Long id) {
        blogService.deleteBlogById(id);
        return ApiResponse.<Void>builder().build();
    }
}
