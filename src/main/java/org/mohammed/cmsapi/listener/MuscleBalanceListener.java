package org.mohammed.cmsapi.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mohammed.cmsapi.constants.MuscleBalanceConstants;
import org.mohammed.cmsapi.dto.MinioUploadedFileGetDto;
import org.mohammed.cmsapi.exception.MuscleBalanceCreationException;
import org.mohammed.cmsapi.exception.MuscleBalanceDeleteException;
import org.mohammed.cmsapi.exception.MuscleBalanceUpdateException;
import org.mohammed.cmsapi.model.MuscleBalance;
import org.mohammed.cmsapi.service.MinioService;

@Slf4j
@RequiredArgsConstructor
public class MuscleBalanceListener {

    private final MinioService minioService;

    @PrePersist
    public void uploadFileToMinio(MuscleBalance muscleBalance) {
        try {
            if (muscleBalance.getId() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(muscleBalance.getFile(), MuscleBalanceConstants.BUCKET_NAME);
                muscleBalance.setImage(uploadedFileGetDto.object());
            }
        } catch (Exception e) {
            throw new MuscleBalanceCreationException("Error happened while Creating this Muscle Balance");
        }
    }

    @PreUpdate
    public void updateFileInMinio(MuscleBalance muscleBalance) {
        try {
            if (muscleBalance.getId() != null) {
                MinioUploadedFileGetDto uploadedFileGetDto = minioService.uploadFile(muscleBalance.getFile(), MuscleBalanceConstants.BUCKET_NAME);
                String oldImage = muscleBalance.getImage();
                muscleBalance.setImage(uploadedFileGetDto.object());
                minioService.deleteFile(MuscleBalanceConstants.BUCKET_NAME, oldImage);
            }
        } catch (Exception e) {
            throw new MuscleBalanceUpdateException("Error happened while updating this Muscle Balance");
        }
    }

    @PreRemove
    public void deleteFileFromMinio(MuscleBalance muscleBalance) {
        try {
            if (muscleBalance.getImage() != null) {
                String bucketName = MuscleBalanceConstants.BUCKET_NAME;
                minioService.deleteFile(bucketName, muscleBalance.getImage());
                log.info("File with Key {} deleted from bucket {}", muscleBalance.getImage(), bucketName);
            }
        } catch (Exception e) {
            throw new MuscleBalanceDeleteException("Error happened while deleting this Muscle Balance");
        }
    }

}
