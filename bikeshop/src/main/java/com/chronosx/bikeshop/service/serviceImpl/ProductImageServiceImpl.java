package com.chronosx.bikeshop.service.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chronosx.bikeshop.dto.response.ProductImageResponse;
import com.chronosx.bikeshop.entity.ProductDetail;
import com.chronosx.bikeshop.entity.ProductImage;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.ProductImageMapper;
import com.chronosx.bikeshop.repository.ProductImageRepository;
import com.chronosx.bikeshop.service.ProductImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    private final ProductImageMapper productImageMapper;

    private final Cloudinary cloudinary;

    @Override
    public List<ProductImageResponse> uploadProductImagesToCloudinary(
            ProductDetail productDetail, List<MultipartFile> images) throws IOException {
        if (images == null || images.isEmpty()) return null;

        List<ProductImage> productImages = new ArrayList<>();
        for (MultipartFile image : images) {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("url").toString();
            String publicId = uploadResult.get("public_id").toString();

            ProductImage productImage = ProductImage.builder()
                    .url(url)
                    .publicId(publicId)
                    .productDetail(productDetail)
                    .build();

            productImages.add(productImage);
        }

        List<ProductImage> savedProductImages = productImageRepository.saveAll(productImages);

        return savedProductImages.stream().map(this::toProductImageResponse).toList();
    }

    @Override
    public String deleteImage(Long id) throws IOException {
        var productImage =
                productImageRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.IMAGE_ID_NOT_FOUND));
        Map<?, ?> destroyResult = cloudinary.uploader().destroy(productImage.getPublicId(), ObjectUtils.emptyMap());
        productImageRepository.delete(productImage);
        return destroyResult.get("result").toString();
    }

    private ProductImageResponse toProductImageResponse(ProductImage productImage) {
        ProductImageResponse productImageResponse = productImageMapper.toProductImageResponse(productImage);
        productImageResponse.setProductDetailId(productImage.getProductDetail().getId());
        return productImageResponse;
    }
}
