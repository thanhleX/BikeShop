package com.chronosx.bikeshop.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewBlogCategoryRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.BlogCategoryResponse;
import com.chronosx.bikeshop.service.BlogCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("blog-categories")
public class BlogCategoryController {
    private final BlogCategoryService blogCategoryService;

    @GetMapping
    public ApiResponse<Set<BlogCategoryResponse>> findAllBlogCategories() {
        return ApiResponse.<Set<BlogCategoryResponse>>builder()
                .result(blogCategoryService.findAllBlogCategories())
                .build();
    }

    @PostMapping
    public ApiResponse<BlogCategoryResponse> addNewBlogCategory(@RequestBody CreateNewBlogCategoryRequest request) {
        return ApiResponse.<BlogCategoryResponse>builder()
                .result(blogCategoryService.addNewBlogCategory(request))
                .build();
    }
}
