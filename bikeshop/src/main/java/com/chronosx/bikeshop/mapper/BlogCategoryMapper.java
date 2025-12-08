package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.BlogCategoryDto;
import com.chronosx.bikeshop.dto.request.CreateNewBlogCategoryRequest;
import com.chronosx.bikeshop.dto.response.BlogCategoryResponse;
import com.chronosx.bikeshop.entity.BlogCategory;

@Mapper(componentModel = "spring")
public interface BlogCategoryMapper {
    BlogCategoryResponse toBlogCategoryResponse(BlogCategory blogCategory);

    BlogCategory toBlogCategory(CreateNewBlogCategoryRequest request);

    BlogCategoryDto toBlogCategoryDto(BlogCategory blogCategory);
}
