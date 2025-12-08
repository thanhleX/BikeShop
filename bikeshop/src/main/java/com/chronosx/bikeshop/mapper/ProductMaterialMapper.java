package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.ProductMaterialDto;
import com.chronosx.bikeshop.dto.request.CreateNewProductMaterialRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductMaterialRequest;
import com.chronosx.bikeshop.dto.response.ProductMaterialResponse;
import com.chronosx.bikeshop.entity.ProductMaterial;

@Mapper(componentModel = "spring")
public interface ProductMaterialMapper {
    ProductMaterialDto toProductMaterialDto(ProductMaterial productMaterials);

    ProductMaterialResponse toProductMaterialResponse(ProductMaterial productMaterial);

    ProductMaterial toProductMaterial(CreateNewProductMaterialRequest request);

    void updateProductMaterialByRequest(
            UpdateProductMaterialRequest request, @MappingTarget ProductMaterial productMaterial);
}
