package com.chronosx.bikeshop.dto.response;

import java.util.Set;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Boolean isActive;
    private String thumbnailUrl;
    private String thumbnailPublicId;
    private Set<RoleResponse> roles;
}
