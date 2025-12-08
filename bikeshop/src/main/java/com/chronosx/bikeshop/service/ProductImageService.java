package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.chronosx.bikeshop.dto.response.ProductImageResponse;
import com.chronosx.bikeshop.entity.ProductDetail;

public interface ProductImageService {

    List<ProductImageResponse> uploadProductImagesToCloudinary(ProductDetail productDetail, List<MultipartFile> images)
            throws IOException;

    String deleteImage(Long id) throws IOException;
}
