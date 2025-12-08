package com.chronosx.bikeshop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductCategoryRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponse;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponseSimple;
import com.chronosx.bikeshop.service.ProductCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("product-categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ApiResponse<Page<ProductCategoryResponseSimple>> findAllProductCategoriesWithPagination(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductCategoryResponseSimple>>builder()
                .result(productCategoryService.findAllProductCategoriesWithPagination(page, size))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductCategoryResponseSimple> addNewProductCategory(
            @RequestBody CreateNewProductCategoryRequest request) {
        return ApiResponse.<ProductCategoryResponseSimple>builder()
                .result(productCategoryService.addNewProductCategory(request))
                .build();
    }

    @GetMapping("all")
    public ApiResponse<List<ProductCategoryResponse>> findAllProductCategories() {
        return ApiResponse.<List<ProductCategoryResponse>>builder()
                .result(productCategoryService.findAllProductCategories())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
        return ApiResponse.builder().build();
    }
}
