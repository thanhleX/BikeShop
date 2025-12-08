package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.chronosx.bikeshop.dto.request.CreateNewOrderRequest;
import com.chronosx.bikeshop.dto.response.OrderResponse;

public interface OrderService {
    Page<OrderResponse> findAllOrders(int page, int size);

    List<OrderResponse> findAllOrdersInCurrentMonth();

    OrderResponse findOrderById(Long id);

    OrderResponse addNewOrder(CreateNewOrderRequest request) throws IOException;

    OrderResponse setPendingAfterPaidById(Long id, String token);

    OrderResponse confirmOrderById(Long id);

    OrderResponse denyOrderById(Long id);
}
