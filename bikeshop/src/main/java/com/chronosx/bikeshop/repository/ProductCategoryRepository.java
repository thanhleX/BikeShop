package com.chronosx.bikeshop.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("select a from ProductCategory a where a.isActive = true")
    @NotNull
    Page<ProductCategory> findAll(@NotNull Pageable pageable);

    @Query("select a from ProductCategory a where a.isActive = true")
    @NotNull
    List<ProductCategory> findAll();

    @Query("select a from ProductCategory a where a.id = ?1 and a.isActive = true")
    @NotNull
    Optional<ProductCategory> findById(@NotNull Long id);

    boolean existsProductCategoriesByName(String name);
}
