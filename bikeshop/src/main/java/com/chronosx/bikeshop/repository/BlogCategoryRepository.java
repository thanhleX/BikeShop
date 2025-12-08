package com.chronosx.bikeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.BlogCategory;

@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {}
