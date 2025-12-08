package com.chronosx.bikeshop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethodResponse {
    private Long id;
    private String name;
    private String logo;
}
