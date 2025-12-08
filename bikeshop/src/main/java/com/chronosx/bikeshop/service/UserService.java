package com.chronosx.bikeshop.service;

import java.io.IOException;
import java.util.List;

import com.chronosx.bikeshop.dto.request.ChangePasswordRequest;
import com.chronosx.bikeshop.dto.request.CreateNewUserRequest;
import com.chronosx.bikeshop.dto.request.UpdateUserRequest;
import com.chronosx.bikeshop.dto.response.UserResponse;

public interface UserService {
    List<UserResponse> findAllUser();

    UserResponse findUserById(Long id);

    UserResponse addNewUser(CreateNewUserRequest request) throws IOException;

    UserResponse updateUser(UpdateUserRequest request, Long id) throws IOException;

    void deleteUser(Long id);

    void changePassword(Long id, ChangePasswordRequest request);

    void resetPassword(Long id);
}
