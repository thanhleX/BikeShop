package com.chronosx.bikeshop.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.service.ProductImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class ProductImageController {
    private final ProductImageService productImageService;

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteImage(@PathVariable Long id) throws IOException {
        return ApiResponse.<String>builder()
                .result(productImageService.deleteImage(id))
                .build();
    }
}
