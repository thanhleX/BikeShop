package com.chronosx.bikeshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductHandlebarRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductHandlebarRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductHandlebarResponse;
import com.chronosx.bikeshop.service.ProductHandlebarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("handlebar")
public class ProductHandlebarController {
    private final ProductHandlebarService productHandlebarService;

    @GetMapping
    public ApiResponse<List<ProductHandlebarResponse>> findAllProductHandlebar() {
        return ApiResponse.<List<ProductHandlebarResponse>>builder()
                .result(productHandlebarService.findAllProductHandlebar())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductHandlebarResponse> findProductHandlebarById(@PathVariable Long id) {
        return ApiResponse.<ProductHandlebarResponse>builder()
                .result(productHandlebarService.findProductHandlebarById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductHandlebarResponse> addNewProductHandlebar(@RequestBody CreateNewProductHandlebarRequest request) {
        return ApiResponse.<ProductHandlebarResponse>builder()
                .result(productHandlebarService.addNewProductHandlebar(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductHandlebarResponse> updateProductHandlebarById(
            @PathVariable Long id, @RequestBody UpdateProductHandlebarRequest request) {
        return ApiResponse.<ProductHandlebarResponse>builder()
                .result(productHandlebarService.updateProductHandlebarById(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProductHandlebarById(@PathVariable Long id) {
        productHandlebarService.deleteProductHandlebarById(id);
        return ApiResponse.builder().build();
    }
}
