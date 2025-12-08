package com.chronosx.bikeshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewProductMaterialRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductMaterialRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.ProductMaterialResponse;
import com.chronosx.bikeshop.service.ProductMaterialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("material")
public class ProductMaterialController {
    private final ProductMaterialService productMaterialService;

    @GetMapping
    public ApiResponse<List<ProductMaterialResponse>> findAllProductMaterial() {
        return ApiResponse.<List<ProductMaterialResponse>>builder()
                .result(productMaterialService.findAllProductMaterials())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductMaterialResponse> findProductMaterialById(@PathVariable Long id) {
        return ApiResponse.<ProductMaterialResponse>builder()
                .result(productMaterialService.findProductMaterialById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<ProductMaterialResponse> addNewProductMaterial(CreateNewProductMaterialRequest request) {
        return ApiResponse.<ProductMaterialResponse>builder()
                .result(productMaterialService.addNewProductMaterial(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductMaterialResponse> updateProductMaterialById(
            @PathVariable Long id, UpdateProductMaterialRequest request) {
        return ApiResponse.<ProductMaterialResponse>builder()
                .result(productMaterialService.updateProductMaterialById(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProductMaterialById(@PathVariable Long id) {
        productMaterialService.deleteProductMaterialById(id);
        return ApiResponse.builder().build();
    }
}
