package com.chronosx.bikeshop.dto.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductDetailRequest {
    private Long colorId;
    private Long handlebarId;
    private Long materialId;
    private Integer stock;
    private List<MultipartFile> images;
}
