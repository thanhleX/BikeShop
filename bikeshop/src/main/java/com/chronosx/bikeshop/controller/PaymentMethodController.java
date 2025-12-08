package com.chronosx.bikeshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.PaymentMethodResponse;
import com.chronosx.bikeshop.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment-method")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping()
    public ApiResponse<List<PaymentMethodResponse>> findAllPaymentMethod() {
        return ApiResponse.<List<PaymentMethodResponse>>builder()
                .result(paymentMethodService.findAllPaymentMethod())
                .build();
    }
}
