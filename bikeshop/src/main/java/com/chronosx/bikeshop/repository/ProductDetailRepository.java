package com.chronosx.bikeshop.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chronosx.bikeshop.entity.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query("select a from ProductDetail a where a.product.name = ?1 and a.isActive is true ")
    List<ProductDetail> findProductDetailByProductName(String productName);

    // new
    @Query("select a from ProductDetail a where a.product.id = ?1 and a.isActive is true")
    Page<ProductDetail> findProductDetailByProductIdWithPagination(Long productId, Pageable pageable);

    @Query("select a from ProductDetail  a where a.id =?1 and a.isActive is true")
    @NotNull
    Optional<ProductDetail> findById(@NotNull Long id);
}
