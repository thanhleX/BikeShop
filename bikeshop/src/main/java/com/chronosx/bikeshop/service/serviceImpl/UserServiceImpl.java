package com.chronosx.bikeshop.service.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chronosx.bikeshop.dto.request.ChangePasswordRequest;
import com.chronosx.bikeshop.dto.request.CreateNewUserRequest;
import com.chronosx.bikeshop.dto.request.UpdateUserRequest;
import com.chronosx.bikeshop.dto.response.UserResponse;
import com.chronosx.bikeshop.entity.User;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.chronosx.bikeshop.mapper.RoleMapper;
import com.chronosx.bikeshop.mapper.UserMapper;
import com.chronosx.bikeshop.repository.RoleRepository;
import com.chronosx.bikeshop.repository.UserRepository;
import com.chronosx.bikeshop.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    private final UploadService uploadService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<UserResponse> findAllUser() {
        return userRepository.findAll().stream().map(this::toUserResponse).toList();
    }

    @Override
    //    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND));
        return toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public UserResponse addNewUser(CreateNewUserRequest request) throws IOException {
        if (!request.getPassword().equals(request.getRePassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);

        if (userRepository.existsUserByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USERNAME_DUPLICATED);

        if (userRepository.existsUserByEmail(request.getEmail())) throw new AppException(ErrorCode.EMAIL_DUPLICATED);

        String password = passwordEncoder.encode(request.getPassword());

        User user = userMapper.toUser(request);
        user.setPassword(password);
        user.setIsActive(true);

        uploadService.uploadThumbnail(user, request.getImage());

        user.setRoles(request.getRoleId().stream()
                .map(roleId -> roleRepository
                        .findById(roleId)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND)))
                .collect(Collectors.toSet()));
        return toUserResponse(userRepository.save(user));
    }

    @Override
    //    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public UserResponse updateUser(UpdateUserRequest request, Long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        userMapper.updateUser(request, user);
        user.setRoles(request.getRoleId().stream()
                .map(roleId -> roleRepository
                        .findById(roleId)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND)))
                .collect(Collectors.toSet()));

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            uploadService.uploadThumbnail(user, request.getImage());
        }
        return toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public void changePassword(Long id, ChangePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);

        if (!request.getNewPassword().equals(request.getRePassword()))
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void resetPassword(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));

        String defaultPassword = "123456";

        user.setPassword(passwordEncoder.encode(defaultPassword));
        userRepository.save(user);
    }

    private UserResponse toUserResponse(User user) {
        UserResponse userResponse = userMapper.toUserResponse(user);
        userResponse.setRoles(
                user.getRoles().stream().map(roleMapper::toRoleResponse).collect(Collectors.toSet()));
        return userResponse;
    }
}
