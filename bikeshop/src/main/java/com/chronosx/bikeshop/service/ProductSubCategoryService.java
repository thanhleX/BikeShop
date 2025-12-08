package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewProductSubCategoryRequest;
import com.chronosx.bikeshop.dto.request.UpdateSubCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductSubCategoryResponse;

public interface ProductSubCategoryService {

    Page<ProductSubCategoryResponse> findProductSubCategoriesByProductCategoryIdWithPagination(
            Long productCategoryId, int page, int size);

    ProductSubCategoryResponse addNewProductSubCategory(CreateNewProductSubCategoryRequest request) throws IOException;

    ProductSubCategoryResponse findProductSubCategoryById(Long id);

    Page<ProductSubCategoryResponse> findProductSubCategoryByProductCategoryName(String name, int page, int size);

    ProductSubCategoryResponse updateProductSubCategoryById(UpdateSubCategoryRequest request, Long id)
            throws IOException;

    void deleteProductSubCategoryById(Long id);
}
