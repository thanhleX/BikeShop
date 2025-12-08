package com.chronosx.bikeshop.dto.response;

import java.util.List;

import com.chronosx.bikeshop.dto.ProductSubCategoryDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryResponse {
    private Long id;
    private String name;
    private boolean isActive;
    private List<ProductSubCategoryDto> productSubCategoryDtoList;
}
