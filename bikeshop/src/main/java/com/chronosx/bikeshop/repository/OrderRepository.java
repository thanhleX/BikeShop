package com.chronosx.bikeshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select a from  Orders a where a.createdAt BETWEEN ?1 and ?2 and a.status = 'CONFIRMED' ")
    List<Orders> findOrdersInMonth(LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
