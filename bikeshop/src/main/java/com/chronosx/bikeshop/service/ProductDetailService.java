package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewProductDetailRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductDetailRequest;
import com.chronosx.bikeshop.dto.response.ProductDetailResponse;

public interface ProductDetailService {
    List<ProductDetailResponse> findAllProductDetailByProductName(String productName);

    // new
    Page<ProductDetailResponse> findAllProductDetailByProductId(Long productId, int page, int size);

    ProductDetailResponse addNewProductDetailByProductName(CreateNewProductDetailRequest request) throws IOException;

    ProductDetailResponse updateProductDetailById(Long id, UpdateProductDetailRequest request) throws IOException;

    ProductDetailResponse findProductDetailById(Long id);

    void deleteProductDetailById(Long id);
}
