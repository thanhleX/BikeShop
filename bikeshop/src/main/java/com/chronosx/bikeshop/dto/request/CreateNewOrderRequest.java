package com.chronosx.bikeshop.dto.request;

import java.util.List;

import com.chronosx.bikeshop.dto.CreateNewOrderDetailDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewOrderRequest {
    private String customerName;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Long paymentMethodId;
    private List<CreateNewOrderDetailDto> createNewOrderDetailDtoList;
}
