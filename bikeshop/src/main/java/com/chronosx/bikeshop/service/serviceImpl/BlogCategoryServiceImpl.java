package com.chronosx.bikeshop.service.serviceImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewBlogCategoryRequest;
import com.chronosx.bikeshop.dto.response.BlogCategoryResponse;
import com.chronosx.bikeshop.entity.BlogCategory;
import com.chronosx.bikeshop.mapper.BlogCategoryMapper;
import com.chronosx.bikeshop.repository.BlogCategoryRepository;
import com.chronosx.bikeshop.service.BlogCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogCategoryServiceImpl implements BlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;

    private final BlogCategoryMapper blogCategoryMapper;

    @Override
    public Set<BlogCategoryResponse> findAllBlogCategories() {
        return blogCategoryRepository.findAll().stream()
                .map(blogCategoryMapper::toBlogCategoryResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'WRITER')")
    public BlogCategoryResponse addNewBlogCategory(CreateNewBlogCategoryRequest request) {
        BlogCategory blogCategory = blogCategoryMapper.toBlogCategory(request);

        return blogCategoryMapper.toBlogCategoryResponse(blogCategoryRepository.save(blogCategory));
    }
}
