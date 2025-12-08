package com.chronosx.bikeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.ProductHandlebar;

@Repository
public interface ProductHandlebarRepository extends JpaRepository<ProductHandlebar, Long> {
    @Query("select a from ProductHandlebar a where a.id =?1")
    Optional<ProductHandlebar> findByProductHandlebarId(Long id);
}
