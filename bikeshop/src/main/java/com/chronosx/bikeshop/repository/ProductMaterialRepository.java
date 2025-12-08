package com.chronosx.bikeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.ProductMaterial;

@Repository
public interface ProductMaterialRepository extends JpaRepository<ProductMaterial, Long> {

    @Query("select a from ProductMaterial a where a.id =?1")
    Optional<ProductMaterial> findByProductMaterialId(Long id);
}
