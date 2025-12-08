package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.request.CreateNewBlogRequest;
import com.chronosx.bikeshop.dto.request.UpdateBlogRequest;
import com.chronosx.bikeshop.dto.response.BlogResponse;
import com.chronosx.bikeshop.entity.Blog;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    BlogResponse toBlogResponse(Blog blog);

    Blog toBlog(CreateNewBlogRequest request);

    void updateBlogFromRequest(UpdateBlogRequest request, @MappingTarget Blog blog);
}
