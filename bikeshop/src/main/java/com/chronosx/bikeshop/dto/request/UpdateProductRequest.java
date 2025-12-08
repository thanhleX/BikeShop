package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    private String name;
    private Double price;
    private Float weight;
    private MultipartFile thumbnail;
    private String description;
}
