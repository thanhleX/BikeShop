package com.chronosx.bikeshop.service;

import java.util.Set;

import com.chronosx.bikeshop.dto.request.CreateNewBlogCategoryRequest;
import com.chronosx.bikeshop.dto.response.BlogCategoryResponse;

public interface BlogCategoryService {
    Set<BlogCategoryResponse> findAllBlogCategories();

    BlogCategoryResponse addNewBlogCategory(CreateNewBlogCategoryRequest request);
}
