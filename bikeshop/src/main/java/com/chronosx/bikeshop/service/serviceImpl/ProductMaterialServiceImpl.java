package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewProductMaterialRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductMaterialRequest;
import com.chronosx.bikeshop.dto.response.ProductMaterialResponse;
import com.chronosx.bikeshop.entity.ProductMaterial;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.ProductMaterialMapper;
import com.chronosx.bikeshop.repository.ProductMaterialRepository;
import com.chronosx.bikeshop.service.ProductMaterialService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMaterialServiceImpl implements ProductMaterialService {

    private final ProductMaterialRepository productMaterialRepository;
    private final ProductMaterialMapper productMaterialMapper;

    @Override
    public List<ProductMaterialResponse> findAllProductMaterials() {
        return productMaterialRepository.findAll().stream()
                .map(this::toProductMaterialResponse)
                .toList();
    }

    @Override
    public ProductMaterialResponse findProductMaterialById(Long id) {
        return toProductMaterialResponse(productMaterialRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_MATERIAL_ID_NOT_FOUND)));
    }

    @Override
    public ProductMaterialResponse addNewProductMaterial(CreateNewProductMaterialRequest request) {
        var ProductMaterial = productMaterialMapper.toProductMaterial(request);
        return toProductMaterialResponse(productMaterialRepository.save(ProductMaterial));
    }

    @Override
    public ProductMaterialResponse updateProductMaterialById(UpdateProductMaterialRequest request, Long id) {
        var ProductMaterial = productMaterialRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND));
        productMaterialMapper.updateProductMaterialByRequest(request, ProductMaterial);
        return toProductMaterialResponse(productMaterialRepository.save(ProductMaterial));
    }

    @Override
    public void deleteProductMaterialById(Long id) {
        var ProductMaterial = productMaterialRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND));
        productMaterialRepository.delete(ProductMaterial);
    }

    private ProductMaterialResponse toProductMaterialResponse(ProductMaterial productMaterial) {
        return productMaterialMapper.toProductMaterialResponse(productMaterial);
    }
}
