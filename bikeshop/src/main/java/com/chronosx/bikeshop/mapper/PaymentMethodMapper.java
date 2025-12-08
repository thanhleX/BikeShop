package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.response.PaymentMethodResponse;
import com.chronosx.bikeshop.entity.PaymentMethod;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod);
}
