package com.chronosx.bikeshop.dto.response;

import java.util.List;

import com.chronosx.bikeshop.dto.ProductColorDto;
import com.chronosx.bikeshop.dto.ProductDto;
import com.chronosx.bikeshop.dto.ProductHandlebarDto;
import com.chronosx.bikeshop.dto.ProductMaterialDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDetailResponse {
    private Long id;
    private Integer stock;

    @JsonProperty(value = "isActive")
    private boolean isActive;

    private ProductDto productDto;
    private ProductColorDto productColorDto;
    private ProductHandlebarDto productHandlebarDto;
    private ProductMaterialDto productMaterialDto;
    private List<ProductImageResponse> productImageResponseList;
}
