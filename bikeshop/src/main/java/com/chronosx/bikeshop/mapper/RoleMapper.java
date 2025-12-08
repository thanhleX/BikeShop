package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;

import com.chronosx.bikeshop.dto.response.RoleResponse;
import com.chronosx.bikeshop.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);
}
