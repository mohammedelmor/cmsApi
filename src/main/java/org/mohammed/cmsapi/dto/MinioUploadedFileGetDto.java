package org.mohammed.cmsapi.dto;

import jakarta.validation.constraints.NotNull;

public record MinioUploadedFileGetDto(@NotNull String object) {
}
