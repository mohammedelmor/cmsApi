package org.mohammed.cmsapi.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record MuscleBalancePostDto(@NotNull(message = "Name can't be empty") String name,
                                   @NotNull(message = "Image can't be null") MultipartFile image) {
}
