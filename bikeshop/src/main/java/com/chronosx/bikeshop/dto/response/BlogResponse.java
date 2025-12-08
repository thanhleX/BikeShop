package com.chronosx.bikeshop.dto.response;

import java.time.LocalDateTime;

import com.chronosx.bikeshop.dto.BlogCategoryDto;
import com.chronosx.bikeshop.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogResponse {
    private Long id;
    private String title;
    private String subTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    private String description;
    private String thumbnailUrl;
    private String thumbnailPublicId;
    private Boolean isActive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime carouselAt;

    private UserDto userDto;
    private BlogCategoryDto blogCategoryDto;
}
