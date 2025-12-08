package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.response.OrderDetailResponse;
import com.chronosx.bikeshop.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);
}
