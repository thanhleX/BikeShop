package com.chronosx.bikeshop.service;

import java.util.List;

import com.chronosx.bikeshop.dto.request.CreateNewProductMaterialRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductMaterialRequest;
import com.chronosx.bikeshop.dto.response.ProductMaterialResponse;

public interface ProductMaterialService {
    List<ProductMaterialResponse> findAllProductMaterials();

    ProductMaterialResponse findProductMaterialById(Long id);

    ProductMaterialResponse addNewProductMaterial(CreateNewProductMaterialRequest request);

    ProductMaterialResponse updateProductMaterialById(UpdateProductMaterialRequest request, Long id);

    void deleteProductMaterialById(Long id);
}
