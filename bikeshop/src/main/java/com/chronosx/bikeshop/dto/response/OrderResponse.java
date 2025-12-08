package com.chronosx.bikeshop.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    private String status;
    private String paymentUrl;
    private PaymentMethodResponse paymentMethodResponse;
    private List<OrderDetailResponse> orderDetailResponseList;
}
