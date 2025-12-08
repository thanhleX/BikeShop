package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.ProductDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductRequest;
import com.chronosx.bikeshop.dto.response.ProductResponse;
import com.chronosx.bikeshop.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);

    Product toProduct(CreateNewProductRequest request);

    ProductDto toProductDto(Product product);

    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);
}
