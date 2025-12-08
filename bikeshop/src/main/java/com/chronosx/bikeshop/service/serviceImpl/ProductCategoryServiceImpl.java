package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.ProductSubCategoryDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponse;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponseSimple;
import com.chronosx.bikeshop.entity.ProductCategory;
import com.chronosx.bikeshop.entity.ProductSubCategory;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.ProductCategoryMapper;
import com.chronosx.bikeshop.mapper.ProductSubCategoryMapper;
import com.chronosx.bikeshop.repository.ProductCategoryRepository;
import com.chronosx.bikeshop.repository.ProductSubCategoryRepository;
import com.chronosx.bikeshop.service.ProductCategoryService;
import com.chronosx.bikeshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductService productService;
    private final ProductSubCategoryRepository productSubCategoryRepository;

    private final ProductCategoryMapper productCategoryMapper;
    private final ProductSubCategoryMapper productSubCategoryMapper;

    @Override
    public Page<ProductCategoryResponseSimple> findAllProductCategoriesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productCategoryRepository.findAll(pageable).map(productCategoryMapper::toProductCategoryResponseSimple);
    }

    @Override
    public List<ProductCategoryResponse> findAllProductCategories() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();

        return productCategoryList.stream()
                .map(productCategory -> {
                    ProductCategoryResponse productCategoryResponse =
                            productCategoryMapper.toProductCategoryResponse(productCategory);
                    List<ProductSubCategoryDto> productSubCategoryDtoList =
                            productSubCategoryRepository
                                    .findAllProductSubCategoryByProductCategoryName(productCategory.getName())
                                    .stream()
                                    .map(this::toProductSubCategoryDto)
                                    .toList();
                    productCategoryResponse.setProductSubCategoryDtoList(productSubCategoryDtoList);
                    return productCategoryResponse;
                })
                .toList();
    }

    private ProductSubCategoryDto toProductSubCategoryDto(ProductSubCategory productSubCategory) {
        ProductSubCategoryDto productSubCategoryDto =
                productSubCategoryMapper.toProductSubCategoryDto(productSubCategory);
        productSubCategoryDto.setProductResponseList(
                productService.findAllProductByProductSubCategoryId(productSubCategoryDto.getId()));
        return productSubCategoryDto;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ProductCategoryResponseSimple addNewProductCategory(CreateNewProductCategoryRequest request) {
        if (productCategoryRepository.existsProductCategoriesByName(request.getName()))
            throw new AppException(ErrorCode.PRODUCT_CATEGORY_NAME_EXISTED);

        ProductCategory productCategory = productCategoryMapper.toProductCategory(request);
        productCategory.setIsActive(true);
        return productCategoryMapper.toProductCategoryResponseSimple(productCategoryRepository.save(productCategory));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR','SALE')")
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_CATEGORY_ID_NOT_FOUND));
        productCategory.setIsActive(false);
        productCategoryRepository.save(productCategory);
    }
}
