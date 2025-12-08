package com.chronosx.bikeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.ProductColor;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

    @Query("select a from ProductColor a where a.id =?1")
    Optional<ProductColor> findByProductColorId(Long id);
}
