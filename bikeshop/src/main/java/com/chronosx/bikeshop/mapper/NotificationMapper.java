package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.response.NotificationResponse;
import com.chronosx.bikeshop.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponse toNotificationResponse(Notification notification);
}
