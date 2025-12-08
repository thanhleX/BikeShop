package com.chronosx.bikeshop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductSubCategoryRequest;
import com.chronosx.bikeshop.dto.request.UpdateSubCategoryRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductSubCategoryResponse;
import com.chronosx.bikeshop.service.ProductSubCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("product-sub-categories")
public class ProductSubCategoryController {
    private final ProductSubCategoryService productSubCategoryService;

    @GetMapping
    public ApiResponse<Page<ProductSubCategoryResponse>> findAllProductSubCategoriesByProductCategoryId(
            @RequestParam Long productCategoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductSubCategoryResponse>>builder()
                .result(productSubCategoryService.findProductSubCategoriesByProductCategoryIdWithPagination(
                        productCategoryId, page, size))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductSubCategoryResponse> findProductSubCategoryById(@PathVariable Long id) {
        return ApiResponse.<ProductSubCategoryResponse>builder()
                .result(productSubCategoryService.findProductSubCategoryById(id))
                .build();
    }

    @GetMapping("name")
    public ApiResponse<Page<ProductSubCategoryResponse>> findProductSubCategoryByName(
            @RequestParam String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductSubCategoryResponse>>builder()
                .result(productSubCategoryService.findProductSubCategoryByProductCategoryName(
                        categoryName, page, size))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductSubCategoryResponse> addNewProductSubCategory(
            @ModelAttribute CreateNewProductSubCategoryRequest request) throws IOException {
        return ApiResponse.<ProductSubCategoryResponse>builder()
                .result(productSubCategoryService.addNewProductSubCategory(request))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ProductSubCategoryResponse> updateProductSubCategoryById(
            @ModelAttribute UpdateSubCategoryRequest request, @PathVariable Long id) throws IOException {
        return ApiResponse.<ProductSubCategoryResponse>builder()
                .result(productSubCategoryService.updateProductSubCategoryById(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<?> deleteProductById(@PathVariable Long id) {
        productSubCategoryService.deleteProductSubCategoryById(id);
        return ApiResponse.builder().build();
    }
}
