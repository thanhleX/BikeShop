package com.chronosx.bikeshop.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductDetailRequest {
    private Integer stock;
    private Long productId;
    private Long colorId;
    private Long handlebarId;
    private Long materialId;
    private List<MultipartFile> images;
}
