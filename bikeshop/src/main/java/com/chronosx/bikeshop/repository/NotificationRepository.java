package com.chronosx.bikeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {}
