package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.ProductColorDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductColorRequest;
import com.chronosx.bikeshop.dto.response.ProductColorResponse;
import com.chronosx.bikeshop.entity.ProductColor;

@Mapper(componentModel = "spring")
public interface ProductColorMapper {
    ProductColorResponse toProductColorResponse(ProductColor productColor);

    ProductColor toProductColor(CreateNewProductColorRequest request);

    ProductColorDto toProductColorDto(ProductColor productColor);
}
