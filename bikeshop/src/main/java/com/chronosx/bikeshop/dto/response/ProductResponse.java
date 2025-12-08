package com.chronosx.bikeshop.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.chronosx.bikeshop.dto.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private Float weight;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnailUrl;
    private String thumbnailPublicId;
    private ProductSubCategoryDto productSubCategoryDto;
    private ProductCategoryDto productCategoryDto;
    private List<ProductDetailDto> productDetailDtoList;
}
