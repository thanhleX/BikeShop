package com.chronosx.bikeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronosx.bikeshop.entity.InvalidToken;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {}
