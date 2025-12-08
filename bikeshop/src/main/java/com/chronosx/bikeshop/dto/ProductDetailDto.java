package com.chronosx.bikeshop.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailDto {
    private Long id;
    private Integer stock;

    @JsonProperty(value = "isActive")
    private boolean isActive;

    private ProductDto productDto;
    private ProductColorDto productColorDto;
    private ProductHandlebarDto productHandlebarDto;
    private ProductMaterialDto productMaterialDto;
    private List<ProductImageDto> productImageDtoList;
}
