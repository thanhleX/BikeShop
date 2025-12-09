package com.chronosx.bikeshop.service.serviceImpl;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewProductSubCategoryRequest;
import com.chronosx.bikeshop.dto.request.UpdateSubCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductSubCategoryResponse;
import com.chronosx.bikeshop.entity.ProductSubCategory;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.ProductCategoryMapper;
import com.chronosx.bikeshop.mapper.ProductSubCategoryMapper;
import com.chronosx.bikeshop.repository.ProductCategoryRepository;
import com.chronosx.bikeshop.repository.ProductSubCategoryRepository;
import com.chronosx.bikeshop.service.ProductSubCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductSubCategoryServiceImpl implements ProductSubCategoryService {
    private final ProductSubCategoryRepository productSubCategoryRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductSubCategoryMapper productSubCategoryMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final UploadService uploadService;

    @Override
    public Page<ProductSubCategoryResponse> findProductSubCategoriesByProductCategoryIdWithPagination(
            Long productCategoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productSubCategoryRepository
                .findAllProductSubCategoryByProductCategoryId(productCategoryId, pageable)
                .map(this::toProductSubCategoryResponse);
    }

    @Override
    public ProductSubCategoryResponse findProductSubCategoryById(Long id) {
        var productSubCategory = productSubCategoryRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_SUB_CATEGORY_ID_NOT_FOUND));
        return toProductSubCategoryResponse(productSubCategory);
    }

    @Override
    public Page<ProductSubCategoryResponse> findProductSubCategoryByProductCategoryName(
            String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productSubCategoryRepository
                .findAllProductSubCategoryByProductCategoryNameWithPagination(categoryName, pageable)
                .map(this::toProductSubCategoryResponse);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ProductSubCategoryResponse addNewProductSubCategory(CreateNewProductSubCategoryRequest request)
            throws IOException {
        if (productSubCategoryRepository.existsProductSubCategoriesByName(request.getName()))
            throw new AppException(ErrorCode.PRODUCT_SUB_CATEGORY_NAME_EXISTED);

        ProductSubCategory productSubCategory = productSubCategoryMapper.toSubcategory(request);
        var category = productCategoryRepository
                .findById(request.getProductCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_ID_NOT_FOUND));
        productSubCategory.setProductCategory(category);
        productSubCategory.setIsActive(true);
        uploadService.uploadThumbnail(productSubCategory, request.getThumbnail());
        return this.toProductSubCategoryResponse(productSubCategoryRepository.save(productSubCategory));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','SALE')")
    public ProductSubCategoryResponse updateProductSubCategoryById(UpdateSubCategoryRequest request, Long id)
            throws IOException {
        ProductSubCategory productSubCategory = productSubCategoryRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_SUB_CATEGORY_ID_NOT_FOUND));
        productSubCategoryMapper.updateProductSubCategoryByRequest(request, productSubCategory);

        if (request.getThumbnail() != null) {
            uploadService.deleteThumbnail(productSubCategory);
            uploadService.uploadThumbnail(productSubCategory, request.getThumbnail());
        }

        return toProductSubCategoryResponse(productSubCategoryRepository.save(productSubCategory));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','SALE')")
    public void deleteProductSubCategoryById(Long id) {
        ProductSubCategory productSubCategory = productSubCategoryRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_SUB_CATEGORY_ID_NOT_FOUND));
        productSubCategory.setIsActive(false);
        productSubCategoryRepository.save(productSubCategory);
    }

    private ProductSubCategoryResponse toProductSubCategoryResponse(ProductSubCategory productSubCategory) {
        ProductSubCategoryResponse categoryResponse =
                productSubCategoryMapper.toSubCategoryResponse(productSubCategory);
        categoryResponse.setProductCategoryDto(
                productCategoryMapper.toProductCategoryDto(productSubCategory.getProductCategory()));
        return categoryResponse;
    }
}
