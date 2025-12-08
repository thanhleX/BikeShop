package com.chronosx.bikeshop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductMaterialRequest {
    private String material;
}
