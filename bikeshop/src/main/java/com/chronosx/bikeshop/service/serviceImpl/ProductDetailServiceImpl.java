package com.chronosx.bikeshop.service.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewProductDetailRequest;
import com.chronosx.bikeshop.dto.request.UpdateProductDetailRequest;
import com.chronosx.bikeshop.dto.response.ProductDetailResponse;
import com.chronosx.bikeshop.dto.response.ProductImageResponse;
import com.chronosx.bikeshop.entity.*;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.*;
import com.chronosx.bikeshop.repository.*;
import com.chronosx.bikeshop.service.ProductDetailService;
import com.chronosx.bikeshop.service.ProductImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;
    private final ProductHandlebarRepository productHandlebarRepository;
    private final ProductMaterialRepository productMaterialRepository;
    private final ProductColorRepository productColorRepository;

    private final ProductDetailMapper productDetailMapper;
    private final ProductMapper productMapper;
    private final ProductColorMapper productColorMapper;
    private final ProductHandlebarMapper productHandlebarMapper;
    private final ProductMaterialMapper productMaterialMapper;
    private final ProductImageMapper productImageMapper;

    private final ProductImageService productImageService;

    @Override
    public List<ProductDetailResponse> findAllProductDetailByProductName(String productName) {
        return productDetailRepository.findProductDetailByProductName(productName).stream()
                .map(this::toProductDetailResponse)
                .toList();
    }

    // new
    @Override
    public Page<ProductDetailResponse> findAllProductDetailByProductId(Long productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDetailRepository
                .findProductDetailByProductIdWithPagination(productId, pageable)
                .map(this::toProductDetailResponse);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ProductDetailResponse addNewProductDetailByProductName(CreateNewProductDetailRequest request)
            throws IOException {
        ProductDetail productDetail = productDetailMapper.toProductDetail(request);

        Product product = productRepository
                .findProductByProductId(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ID_NOT_FOUND));
        productDetail.setProduct(product);

        validateAndApplyAttributes (productDetail, request.getMaterialId(), request.getHandlebarId(), request.getColorId());

        productDetail.setActive(true);

        validateDuplicateDetail(productDetail);

        var newProductDetail = productDetailRepository.save(productDetail);
        ProductDetailResponse productDetailResponse = this.toProductDetailResponse(newProductDetail);

        List<ProductImageResponse> productImageResponseList =
                productImageService.uploadProductImagesToCloudinary(newProductDetail, request.getImages());
        productDetailResponse.setProductImageResponseList(productImageResponseList);

        return productDetailResponse;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','SALE')")
    public ProductDetailResponse updateProductDetailById(Long id, UpdateProductDetailRequest request)
            throws IOException {
        var productDetail = productDetailRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_ID_NOT_FOUND));
        productDetailMapper.updateProductDetail(request, productDetail);

        validateAndApplyAttributes (productDetail, request.getMaterialId(), request.getHandlebarId(), request.getColorId());

        validateDuplicateDetail(productDetail);

        var productDetailResponse = toProductDetailResponse(productDetailRepository.save(productDetail));

        if (request.getImages() != null) {
            List<ProductImageResponse> productImageResponseList =
                    productImageService.uploadProductImagesToCloudinary(productDetail, request.getImages());
            productImageResponseList.forEach(productImageResponse ->
                    productDetailResponse.getProductImageResponseList().add(productImageResponse));
        }

        return productDetailResponse;
    }

    private void validateAndApplyAttributes (ProductDetail productDetail, Long materialId, Long handlebarId, Long colorId) {

        if (handlebarId != null) {
            ProductHandlebar handlebar = productHandlebarRepository
                    .findByProductHandlebarId(handlebarId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_HANDLEBAR_ID_NOT_FOUND));
            productDetail.setProductHandlebar(handlebar);
        }

        if (materialId != null) {
            ProductMaterial material = productMaterialRepository
                    .findByProductMaterialId(materialId)
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_MATERIAL_ID_NOT_FOUND));
            productDetail.setProductMaterial(material);
        }

        ProductColor color = productColorRepository
                .findByProductColorId(colorId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_COLOR_ID_NOT_FOUND));
        productDetail.setProductColor(color);
    }

    private void validateDuplicateDetail(ProductDetail productDetail) {
        productDetailRepository
                .findProductDetailByProductName(productDetail.getProduct().getName())
                .forEach(existing -> {
                    if (existing != productDetail
                            && existing.getProductMaterial() == productDetail.getProductMaterial()
                            && existing.getProductColor() == productDetail.getProductColor()
                            && existing.getProductHandlebar() == productDetail.getProductHandlebar()) {
                        throw new AppException(ErrorCode.PRODUCT_DETAIL_DUPLICATED);
                    }
                });
    }

    @Override
    public ProductDetailResponse findProductDetailById(Long id) {
        var productDetail = productDetailRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_ID_NOT_FOUND));
        return toProductDetailResponse(productDetail);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','SALE')")
    public void deleteProductDetailById(Long id) {
        var productDetail = productDetailRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_DETAIL_ID_NOT_FOUND));
        productDetail.setActive(false);
        productDetailRepository.save(productDetail);
    }

    public ProductDetailResponse toProductDetailResponse(ProductDetail productDetail) {
        ProductDetailResponse res = productDetailMapper.toProductDetailResponse(productDetail);

        res.setProductDto(productMapper.toProductDto(productDetail.getProduct()));
        res.setProductColorDto(productColorMapper.toProductColorDto(productDetail.getProductColor()));
        res.setProductHandlebarDto(productHandlebarMapper.toProductHandlebarDto(productDetail.getProductHandlebar()));
        res.setProductMaterialDto(productMaterialMapper.toProductMaterialDto(productDetail.getProductMaterial()));
        res.setProductImageResponseList(
                productImageMapper.toProductImageResponseList(productDetail.getProductImages()));

        if (res.getProductImageResponseList() != null) {
            res.getProductImageResponseList().forEach(img -> img.setProductDetailId(res.getId()));
        }
        return res;
    }
}
