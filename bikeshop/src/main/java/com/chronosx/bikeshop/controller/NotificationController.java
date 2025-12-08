package com.chronosx.bikeshop.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.NotificationResponse;
import com.chronosx.bikeshop.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("set-to-read/{id}")
    public ApiResponse<?> setReadNotification(@PathVariable Long id) {
        notificationService.setReadNotificationById(id);
        return ApiResponse.builder().build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<Page<NotificationResponse>> findALlNotificationsByUserId(
            @PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        return ApiResponse.<Page<NotificationResponse>>builder()
                .result(notificationService.findAllNotificationByUserId(userId, page, size))
                .build();
    }
}
