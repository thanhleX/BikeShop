package com.chronosx.bikeshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.chronosx.bikeshop.dto.UserDto;
import com.chronosx.bikeshop.dto.request.CreateNewUserRequest;
import com.chronosx.bikeshop.dto.request.UpdateUserRequest;
import com.chronosx.bikeshop.dto.response.UserResponse;
import com.chronosx.bikeshop.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    UserResponse toUserResponse(User user);

    User toUser(CreateNewUserRequest request);

    void updateUser(UpdateUserRequest request, @MappingTarget User user);
}
