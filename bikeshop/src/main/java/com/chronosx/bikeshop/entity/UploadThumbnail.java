package com.chronosx.bikeshop.entity;

public interface UploadThumbnail {
    void setThumbnailUrl(String url);

    void setThumbnailPublicId(String publicId);

    String getThumbnailUrl();

    String getThumbnailPublicId();
}
