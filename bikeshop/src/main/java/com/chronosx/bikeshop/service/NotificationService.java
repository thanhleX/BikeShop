package com.chronosx.bikeshop.service;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.response.NotificationResponse;

public interface NotificationService {

    void setReadNotificationById(Long id);

    Page<NotificationResponse> findAllNotificationByUserId(Long userId, int page, int size);
}
