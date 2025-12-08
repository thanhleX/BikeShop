package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.ProductSubCategoryDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductSubCategoryRequest;
import com.chronosx.bikeshop.dto.request.UpdateSubCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductSubCategoryResponse;
import com.chronosx.bikeshop.entity.ProductSubCategory;

@Mapper(componentModel = "spring")
public interface ProductSubCategoryMapper {
    ProductSubCategory toSubcategory(CreateNewProductSubCategoryRequest request);

    ProductSubCategoryResponse toSubCategoryResponse(ProductSubCategory productSubCategory);

    ProductSubCategoryDto toProductSubCategoryDto(ProductSubCategory productSubCategory);

    void updateProductSubCategoryByRequest(
            UpdateSubCategoryRequest request, @MappingTarget ProductSubCategory productSubCategory);
}
