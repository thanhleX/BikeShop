package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.response.PaymentMethodResponse;
import com.chronosx.bikeshop.mapper.PaymentMethodMapper;
import com.chronosx.bikeshop.repository.PaymentMethodRepository;
import com.chronosx.bikeshop.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    @Override
    public List<PaymentMethodResponse> findAllPaymentMethod() {
        return paymentMethodRepository.findAll().stream()
                .map(paymentMethodMapper::toPaymentMethodResponse)
                .toList();
    }
}
