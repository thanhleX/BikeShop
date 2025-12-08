package com.chronosx.bikeshop.dto;

import java.util.List;

import com.chronosx.bikeshop.dto.response.ProductResponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductSubCategoryDto {
    private Long id;
    private String name;
    private List<ProductResponse> productResponseList;
}
