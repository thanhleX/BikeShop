package com.chronosx.bikeshop.dto.request;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewUserRequest {
    private String username;
    private String password;
    private String rePassword;
    private String fullName;
    private String email;
    private String phone;
    private MultipartFile image;
    private Set<Long> roleId;
}
