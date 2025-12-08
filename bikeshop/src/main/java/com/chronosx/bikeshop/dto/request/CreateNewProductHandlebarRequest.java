package com.chronosx.bikeshop.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewProductHandlebarRequest {
    private String style;
}
