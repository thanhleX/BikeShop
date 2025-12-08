package com.chronosx.bikeshop.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNewBlogRequest {
    private String title;
    private String subTitle;
    private String description;
    private MultipartFile thumbnail;
    private Long blogCategoryId;
    private Long userId;
}
