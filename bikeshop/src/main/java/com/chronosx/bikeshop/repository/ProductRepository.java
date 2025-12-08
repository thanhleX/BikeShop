package com.chronosx.bikeshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select a from Product a where a.name =?1 and a.isActive = true")
    Optional<Product> findProductByProductName(String name);

    @Query("select a from Product a where a.productSubCategory.id = ?1 and a.isActive = true")
    List<Product> findAllProductByProductSubCategoryId(Long productSubCategoryId);

    @Query("select a from Product a where a.productSubCategory.name = ?1 and a.isActive = true")
    Page<Product> findAllProductByProductSubCategoryNameWithPagination(String name, Pageable pageable);

    // new
    @Query("select a from Product a where a.productSubCategory.id = ?1 and a.isActive = true")
    Page<Product> findAllProductByProductSubCategoryIdWithPagination(Long productSubCategoryId, Pageable pageable);

    @Query("select a from Product a where a.id = ?1 and a.isActive = true")
    Optional<Product> findProductByProductId(Long id);
}
