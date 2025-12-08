package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewProductRequest {
    private String name;
    private Double price;
    private Float weight;
    private MultipartFile thumbnail;
    private String description;
    private Long productSubCategoryId;
}
