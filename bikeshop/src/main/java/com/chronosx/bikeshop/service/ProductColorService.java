package com.chronosx.bikeshop.service;

import java.util.List;

import com.chronosx.bikeshop.dto.request.CreateNewProductColorRequest;
import com.chronosx.bikeshop.dto.response.ProductColorResponse;

public interface ProductColorService {
    List<ProductColorResponse> getAllColors();

    ProductColorResponse addNewColor(CreateNewProductColorRequest request);
}
