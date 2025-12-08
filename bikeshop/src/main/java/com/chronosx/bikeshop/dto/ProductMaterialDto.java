package com.chronosx.bikeshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductMaterialDto {
    private Long id;
    private String material;
}
