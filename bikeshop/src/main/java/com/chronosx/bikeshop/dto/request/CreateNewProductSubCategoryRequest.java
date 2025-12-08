package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductSubCategoryRequest {
    private String name;
    private Long productCategoryId;
    private MultipartFile thumbnail;
    private String description;
}
