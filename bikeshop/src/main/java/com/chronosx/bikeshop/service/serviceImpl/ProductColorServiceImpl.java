package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewProductColorRequest;
import com.chronosx.bikeshop.dto.response.ProductColorResponse;
import com.chronosx.bikeshop.mapper.ProductColorMapper;
import com.chronosx.bikeshop.repository.ProductColorRepository;
import com.chronosx.bikeshop.service.ProductColorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductColorServiceImpl implements ProductColorService {
    private final ProductColorRepository productColorRepository;
    private final ProductColorMapper productColorMapper;

    @Override
    public List<ProductColorResponse> getAllColors() {
        return productColorRepository.findAll().stream()
                .map(productColorMapper::toProductColorResponse)
                .toList();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SALE')")
    public ProductColorResponse addNewColor(CreateNewProductColorRequest request) {
        var productColor = productColorMapper.toProductColor(request);
        return productColorMapper.toProductColorResponse(productColorRepository.save(productColor));
    }
}
