package com.chronosx.bikeshop.service;

import java.util.List;

import com.chronosx.bikeshop.dto.response.PaymentMethodResponse;

public interface PaymentMethodService {
    List<PaymentMethodResponse> findAllPaymentMethod();
}
