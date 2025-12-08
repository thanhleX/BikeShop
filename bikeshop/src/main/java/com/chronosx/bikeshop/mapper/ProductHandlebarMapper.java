package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.ProductHandlebarDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductHandlebarRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductHandlebarRequest;
import com.chronosx.bikeshop.dto.response.ProductHandlebarResponse;
import com.chronosx.bikeshop.entity.ProductHandlebar;

@Mapper(componentModel = "spring")
public interface ProductHandlebarMapper {
    ProductHandlebarDto toProductHandlebarDto(ProductHandlebar productHandlebar);

    ProductHandlebarResponse toProductHandlebarResponse(ProductHandlebar productHandlebar);

    ProductHandlebar toProductHandleBar(CreateNewProductHandlebarRequest request);

    void updateProductHandlebarByRequest(
            UpdateProductHandlebarRequest request, @MappingTarget ProductHandlebar productHandlebar);
}
