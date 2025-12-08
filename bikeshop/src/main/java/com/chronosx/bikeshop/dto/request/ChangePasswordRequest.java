package com.chronosx.bikeshop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String rePassword;
}
