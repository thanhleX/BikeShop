package com.chronosx.bikeshop.service;

import java.util.List;

import com.chronosx.bikeshop.dto.request.CreateNewRoleRequest;
import com.chronosx.bikeshop.dto.response.RoleResponse;

public interface RoleService {
    List<RoleResponse> findAllRole();

    RoleResponse addNewRole(CreateNewRoleRequest request);

    RoleResponse findRoleById(Long id);
}
