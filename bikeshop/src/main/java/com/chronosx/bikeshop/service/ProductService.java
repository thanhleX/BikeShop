package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewProductRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductRequest;
import com.chronosx.bikeshop.dto.response.ProductResponse;

public interface ProductService {

    Page<ProductResponse> findAllProductByProductSubCategoryName(String productSubCategoryName, int page, int size);
    
    // new
    Page<ProductResponse> findAllProductByProductSubCategoryId(Long productSubCategoryId, int page, int size);

    List<ProductResponse> findAllProductByProductSubCategoryId(Long id);

    ProductResponse findProductByProductName(String productName);

    ProductResponse findProductById(Long id);

    ProductResponse createNewProduct(CreateNewProductRequest request) throws IOException;

    ProductResponse updateProductById(UpdateProductRequest request, Long id) throws IOException;

    void deleteProductById(Long id);
}
