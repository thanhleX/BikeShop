package com.chronosx.bikeshop.dto.request;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    private String fullName;
    private String email;
    private String phone;
    private MultipartFile image;
    private Set<Long> roleId;
}
