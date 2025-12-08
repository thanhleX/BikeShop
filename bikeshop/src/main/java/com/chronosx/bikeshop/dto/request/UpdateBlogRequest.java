package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBlogRequest {
    private String title;
    private String subTitle;
    private String description;
    private MultipartFile thumbnail;
}
