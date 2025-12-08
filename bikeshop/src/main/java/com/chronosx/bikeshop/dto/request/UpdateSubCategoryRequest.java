package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateSubCategoryRequest {
    private String name;
    private String description;
    private MultipartFile thumbnail;
}
