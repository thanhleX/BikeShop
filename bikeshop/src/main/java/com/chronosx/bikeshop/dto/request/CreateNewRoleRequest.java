package com.chronosx.bikeshop.dto.request;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewRoleRequest {
    private String roleName;
}
