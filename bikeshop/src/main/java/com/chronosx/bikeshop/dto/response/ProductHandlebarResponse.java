package com.chronosx.bikeshop.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductHandlebarResponse {
    private Long id;
    private String style;
}
