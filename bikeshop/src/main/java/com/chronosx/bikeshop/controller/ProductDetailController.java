package com.chronosx.bikeshop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductDetailRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductDetailRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductDetailResponse;
import com.chronosx.bikeshop.service.ProductDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product-detail")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    // @GetMapping()
    // public ApiResponse<List<ProductDetailResponse>> findAllProductDetailByProductName(
    //         @RequestParam String productName) {
    //     return ApiResponse.<List<ProductDetailResponse>>builder()
    //             .result(productDetailService.findAllProductDetailByProductName(productName))
    //             .build();
    // }

    @GetMapping
    public ApiResponse<?> findAllProductDetail(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (productId != null) {
            return ApiResponse.<Page<ProductDetailResponse>>builder()
                    .result(productDetailService.findAllProductDetailByProductId(productId, page, size))
                    .build();
        } else if (productName != null) {
            return ApiResponse.<List<ProductDetailResponse>>builder()
                    .result(productDetailService.findAllProductDetailByProductName(productName))
                    .build();
        } else {
            throw new IllegalArgumentException("Either productId or productName must be provided");
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> findProductDetailById(@PathVariable Long id) {
        return ApiResponse.<ProductDetailResponse>builder()
                .result(productDetailService.findProductDetailById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductDetailResponse> addNewProductDetailByProductId(
            @ModelAttribute CreateNewProductDetailRequest request) throws IOException {
        return ApiResponse.<ProductDetailResponse>builder()
                .result(productDetailService.addNewProductDetailByProductName(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDetailResponse> updateProductDetailById(
            @ModelAttribute UpdateProductDetailRequest request, @PathVariable Long id) throws IOException {
        return ApiResponse.<ProductDetailResponse>builder()
                .result(productDetailService.updateProductDetailById(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProductDetailById(@PathVariable Long id) {
        productDetailService.deleteProductDetailById(id);
        return ApiResponse.builder().build();
    }
}
