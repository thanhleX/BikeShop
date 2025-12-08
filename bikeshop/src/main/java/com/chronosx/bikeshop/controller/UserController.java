package com.chronosx.bikeshop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.chronosx.bikeshop.dto.request.ChangePasswordRequest;
import com.chronosx.bikeshop.dto.request.CreateNewUserRequest;
import com.chronosx.bikeshop.dto.request.UpdateUserRequest;
import com.chronosx.bikeshop.dto.response.ApiResponse;
import com.chronosx.bikeshop.dto.response.UserResponse;
import com.chronosx.bikeshop.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ApiResponse<List<UserResponse>> findAllUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAllUser())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findUserById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findUserById(id))
                .build();
    }

    @PostMapping()
    public ApiResponse<UserResponse> addNewUser(@ModelAttribute CreateNewUserRequest request) throws IOException {
        return ApiResponse.<UserResponse>builder()
                .result(userService.addNewUser(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUserById(@ModelAttribute UpdateUserRequest request, @PathVariable Long id)
            throws IOException {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request, id))
                .build();
    }

    @PutMapping("/{id}/change-password")
    public ApiResponse<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request);
        return ApiResponse.builder().message("Password changed successfully").build();
    }

    @PutMapping("/{id}/reset-password")
    public ApiResponse<?> resetPassword(@PathVariable Long id) {
        userService.resetPassword(id);
        return ApiResponse.builder().message("Password reset to default").build();
    }

    @DeleteMapping("{id}")
    public ApiResponse<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<UserResponse>builder().build();
    }
}
