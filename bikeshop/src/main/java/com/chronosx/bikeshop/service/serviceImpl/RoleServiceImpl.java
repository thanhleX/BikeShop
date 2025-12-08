package com.chronosx.bikeshop.service.serviceImpl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.CreateNewRoleRequest;
import com.chronosx.bikeshop.dto.response.RoleResponse;
import com.chronosx.bikeshop.entity.Role;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.RoleMapper;
import com.chronosx.bikeshop.repository.RoleRepository;
import com.chronosx.bikeshop.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR','SALE')")
    public List<RoleResponse> findAllRole() {
        return roleRepository.findAll().stream().map(this::toRoleResponse).toList();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN')")
    public RoleResponse addNewRole(CreateNewRoleRequest request) {
        if (roleRepository.findRoleByRoleName(request.getRoleName()).isPresent()) {
            throw new AppException(ErrorCode.ROLE_NAME_DUPLICATED);
        }
        Role role = Role.builder().roleName(request.getRoleName()).build();
        return toRoleResponse(roleRepository.save(role));
    }

    @Override
    public RoleResponse findRoleById(Long id) {
        var role = roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND));
        return toRoleResponse(role);
    }

    private RoleResponse toRoleResponse(Role role) {
        return roleMapper.toRoleResponse(role);
    }
}
