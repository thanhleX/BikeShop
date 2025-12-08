package com.chronosx.bikeshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderNotificationDto {
    private Long orderId;
    private String orderStatus;
}
