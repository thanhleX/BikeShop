package com.chronosx.bikeshop.service.serviceImpl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.chronosx.bikeshop.entity.UploadThumbnail;
import com.chronosx.bikeshop.exception.AppException;
import com.chronosx.bikeshop.exception.ErrorCode;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UploadService {
    private final Cloudinary cloudinary;

    public void uploadThumbnail(UploadThumbnail uploadThumbnail, MultipartFile file) throws IOException {
        if (file == null) {
            throw new AppException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();
        String publicId = uploadResult.get("public_id").toString();
        uploadThumbnail.setThumbnailUrl(url);
        uploadThumbnail.setThumbnailPublicId(publicId);
    }

    public void deleteThumbnail(UploadThumbnail uploadThumbnail) throws IOException {
        cloudinary.uploader().destroy(uploadThumbnail.getThumbnailPublicId(), ObjectUtils.emptyMap());
        uploadThumbnail.setThumbnailUrl(null);
        uploadThumbnail.setThumbnailPublicId(null);
    }
}
