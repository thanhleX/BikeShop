package com.chronosx.bikeshop.service;

import java.util.List;

import com.chronosx.bikeshop.dto.request.CreateNewProductHandlebarRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductHandlebarRequest;
import com.chronosx.bikeshop.dto.response.ProductHandlebarResponse;

public interface ProductHandlebarService {
    List<ProductHandlebarResponse> findAllProductHandlebar();

    ProductHandlebarResponse findProductHandlebarById(Long id);

    ProductHandlebarResponse addNewProductHandlebar(CreateNewProductHandlebarRequest request);

    ProductHandlebarResponse updateProductHandlebarById(UpdateProductHandlebarRequest request, Long id);

    void deleteProductHandlebarById(Long id);
}
