package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewProductHandlebarRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductHandlebarRequest;
import com.chronosx.bikeshop.dto.response.ProductHandlebarResponse;
import com.chronosx.bikeshop.entity.ProductHandlebar;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.ProductHandlebarMapper;
import com.chronosx.bikeshop.repository.ProductHandlebarRepository;
import com.chronosx.bikeshop.service.ProductHandlebarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductHandlebarServiceImpl implements ProductHandlebarService {

    private final ProductHandlebarRepository productHandlebarRepository;

    private final ProductHandlebarMapper productHandlebarMapper;

    @Override
    public List<ProductHandlebarResponse> findAllProductHandlebar() {
        return productHandlebarRepository.findAll().stream()
                .map(this::toProductHandlebarResponse)
                .toList();
    }

    @Override
    public ProductHandlebarResponse findProductHandlebarById(Long id) {
        return toProductHandlebarResponse(productHandlebarRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND)));
    }

    @Override
    public ProductHandlebarResponse addNewProductHandlebar(CreateNewProductHandlebarRequest request) {
        var productHandlebar = productHandlebarMapper.toProductHandleBar(request);
        return toProductHandlebarResponse(productHandlebarRepository.save(productHandlebar));
    }

    @Override
    public ProductHandlebarResponse updateProductHandlebarById(UpdateProductHandlebarRequest request, Long id) {
        var productHandlebar = productHandlebarRepository
                .findByProductHandlebarId(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND));
        productHandlebarMapper.updateProductHandlebarByRequest(request, productHandlebar);
        return toProductHandlebarResponse(productHandlebarRepository.save(productHandlebar));
    }

    @Override
    public void deleteProductHandlebarById(Long id) {
        var productHandlebar = productHandlebarRepository
                .findByProductHandlebarId(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND));
        productHandlebarRepository.delete(productHandlebar);
    }

    private ProductHandlebarResponse toProductHandlebarResponse(ProductHandlebar productHandlebar) {
        return productHandlebarMapper.toProductHandlebarResponse(productHandlebar);
    }
}
