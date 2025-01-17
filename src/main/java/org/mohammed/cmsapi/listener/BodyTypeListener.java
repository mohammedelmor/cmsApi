package org.mohammed.cmsapi.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.config.properties.MinioProperties;
import org.mohammed.cmsapi.constants.BodyTypeConstants;
import org.mohammed.cmsapi.dto.MinioUploadedFileGetDto;
import org.mohammed.cmsapi.exception.BodyTypeCreationException;
import org.mohammed.cmsapi.exception.BodyTypeDeleteException;
import org.mohammed.cmsapi.exception.BodyTypeUpdateException;
import org.mohammed.cmsapi.model.BodyType;
import org.mohammed.cmsapi.service.MinioService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BodyTypeListener {

    private final MinioService minioService;
    private final String prefixEndpoint;

    public BodyTypeListener(MinioProperties minioProperties, MinioService minioService) {
        this.minioService = minioService;
        this.prefixEndpoint = String.format("%s/api/v1/buckets/%s/objects/download?preview=true&prefix=", minioProperties.getPreviewEndpoint(), BodyTypeConstants.BUCKET_NAME);
    }

    @PrePersist
    public void uploadFileToMinio(BodyType bodyType) {
        try {
            if (bodyType.getId() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(bodyType.getFile(), BodyTypeConstants.BUCKET_NAME);
                bodyType.setImage(uploadedFileGetDto.object());
                bodyType.setFullPath(prefixEndpoint + uploadedFileGetDto.object());
            }
        } catch (Exception e) {
            throw new BodyTypeCreationException("Error happened while Creating this Body Type");
        }
    }

    @PreUpdate
    public void updateFileInMinio(BodyType bodyType) {
        try {
            if (bodyType.getId() != null && bodyType.getFile() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(bodyType.getFile(), BodyTypeConstants.BUCKET_NAME);
                String oldImage = bodyType.getImage();
                bodyType.setImage(uploadedFileGetDto.object());
                bodyType.setFullPath(prefixEndpoint + uploadedFileGetDto.object());
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
