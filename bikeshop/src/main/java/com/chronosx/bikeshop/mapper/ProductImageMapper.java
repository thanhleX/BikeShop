package com.chronosx.bikeshop.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.ProductImageDto;
import com.chronosx.bikeshop.dto.response.ProductImageResponse;
import com.chronosx.bikeshop.entity.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    ProductImageResponse toProductImageResponse(ProductImage productImage);

    List<ProductImageResponse> toProductImageResponseList(List<ProductImage> productImageList);

    List<ProductImageDto> toProductImageDtoList(List<ProductImage> productImageList);
}
