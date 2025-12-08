package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.ProductCategoryDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductCategoryRequest;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponse;
import com.chronosx.bikeshop.dto.response.ProductCategoryResponseSimple;
import com.chronosx.bikeshop.entity.ProductCategory;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategoryResponseSimple toProductCategoryResponseSimple(ProductCategory productCategory);

    ProductCategoryResponse toProductCategoryResponse(ProductCategory productCategory);

    ProductCategory toProductCategory(CreateNewProductCategoryRequest request);

    ProductCategoryDto toProductCategoryDto(ProductCategory productCategory);
}
