package com.chronosx.bikeshop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewProductCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponse;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponseSimple;

public interface ProductCategoryService {
    Page<ProductCategoryResponseSimple> findAllProductCategoriesWithPagination(int page, int size);

    List<ProductCategoryResponse> findAllProductCategories();

    ProductCategoryResponseSimple addNewProductCategory(CreateNewProductCategoryRequest request);

    void deleteProductCategory(Long id);
}
