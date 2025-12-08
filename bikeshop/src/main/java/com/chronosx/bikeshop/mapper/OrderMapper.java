package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.request.CreateNewOrderRequest;
import com.chronosx.bikeshop.dto.response.OrderResponse;
import com.chronosx.bikeshop.entity.Orders;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Orders orders);

    Orders toOrder(CreateNewOrderRequest request);
}
