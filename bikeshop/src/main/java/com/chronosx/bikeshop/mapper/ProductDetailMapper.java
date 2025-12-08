package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.ProductDetailDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductDetailRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductDetailRequest;
import com.chronosx.bikeshop.dto.response.ProductDetailResponse;
import com.chronosx.bikeshop.entity.ProductDetail;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    ProductDetailResponse toProductDetailResponse(ProductDetail productDetail);

    ProductDetail toProductDetail(CreateNewProductDetailRequest request);

    ProductDetailDto toProductDetailDto(ProductDetail productDetail);

    void updateProductDetail(UpdateProductDetailRequest request, @MappingTarget ProductDetail productDetail);
}
