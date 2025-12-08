package com.chronosx.bikeshop.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductResponse;
import com.chronosx.bikeshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // @GetMapping
    // public ApiResponse<Page<ProductResponse>> findAllProductBySubCategoryName(
    //         @RequestParam String subCategoryName, @RequestParam int page, @RequestParam int size) {
    //     return ApiResponse.<Page<ProductResponse>>builder()
    //             .result(productService.findAllProductByProductSubCategoryName(subCategoryName, page, size))
    //             .build();
    // }
    
    // new
    @GetMapping
    public ApiResponse<Page<ProductResponse>> findAllProductBySubCategoryId(
            @RequestParam(required = false) Long productSubCategoryId,
            @RequestParam(required = false) String subCategoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (productSubCategoryId != null) {
            return ApiResponse.<Page<ProductResponse>>builder()
                    .result(productService.findAllProductByProductSubCategoryId(productSubCategoryId, page, size))
                    .build();
        } else if (subCategoryName != null) {
            return ApiResponse.<Page<ProductResponse>>builder()
                    .result(productService.findAllProductByProductSubCategoryName(subCategoryName, page, size))
                    .build();
        } else {
            throw new IllegalArgumentException("Either productSubCategoryId or subCategoryName must be provided");
        }
    }

    @PostMapping
    public ApiResponse<ProductResponse> addNewProduct(@ModelAttribute CreateNewProductRequest request)
            throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createNewProduct(request))
                .build();
    }

    @GetMapping("name")
    public ApiResponse<ProductResponse> findProductByProductName(@RequestParam String productName) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.findProductByProductName(productName))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> findProductById(@PathVariable Long id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.findProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductByProductId(
            @PathVariable Long id, @ModelAttribute UpdateProductRequest request) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProductById(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ApiResponse.builder().build();
    }
}
