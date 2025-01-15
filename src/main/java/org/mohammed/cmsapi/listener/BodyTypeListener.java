package org.mohammed.cmsapi.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.constants.BodyTypeConstants;
import org.mohammed.cmsapi.dto.MinioUploadedFileGetDto;
import org.mohammed.cmsapi.exception.BodyTypeCreationException;
import org.mohammed.cmsapi.exception.BodyTypeDeleteException;
import org.mohammed.cmsapi.exception.BodyTypeUpdateException;
import org.mohammed.cmsapi.model.BodyType;
import org.mohammed.cmsapi.service.MinioService;

@Slf4j
@RequiredArgsConstructor
public class BodyTypeListener {

    private final MinioService minioService;

    @PrePersist
    public void uploadFileToMinio(BodyType bodyType) {
        try {
            if (bodyType.getId() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(bodyType.getFile(), BodyTypeConstants.BUCKET_NAME);
                bodyType.setImage(uploadedFileGetDto.object());
            }
        } catch (Exception e) {
            throw new BodyTypeCreationException("Error happened while Creating this Body Type");
        }
    }

    @PreUpdate
    public void updateFileInMinio(BodyType bodyType) {
        try {
            if (bodyType.getId() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(bodyType.getFile(), BodyTypeConstants.BUCKET_NAME);
                String oldImage = bodyType.getImage();
                bodyType.setImage(uploadedFileGetDto.object());
                minioService.deleteFile(BodyTypeConstants.BUCKET_NAME, oldImage);
            }
        } catch (Exception e) {
            throw new BodyTypeUpdateException("Error happened while updating this Body Type");
        }
    }

    @PreRemove
    public void deleteFileFromMinio(BodyType bodyType) {
        try {
            if (bodyType.getImage() != null) {
                String bucketName = BodyTypeConstants.BUCKET_NAME;
                minioService.deleteFile(bucketName, bodyType.getImage());
                log.info("File with Key {} deleted from bucket {}", bodyType.getImage(), bucketName);
            }
        } catch (Exception e) {
            throw new BodyTypeDeleteException("Error happened while deleting this Body Type");
        }
    }

}
