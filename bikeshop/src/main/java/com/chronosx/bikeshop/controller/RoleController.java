package com.chronosx.bikeshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.CreateNewRoleRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.RoleResponse;
import com.chronosx.bikeshop.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping()
    ApiResponse<List<RoleResponse>> findAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.findAllRole())
                .build();
    }

    @PostMapping()
    ApiResponse<RoleResponse> addNewRole(@RequestBody CreateNewRoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.addNewRole(request))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<RoleResponse> findRoleById(@PathVariable Long id) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.findRoleById(id))
                .build();
    }
}
