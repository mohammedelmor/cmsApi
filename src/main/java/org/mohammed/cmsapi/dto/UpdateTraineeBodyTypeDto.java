package org.mohammed.cmsapi.dto;


import jakarta.validation.constraints.NotNull;

public record UpdateTraineeBodyTypeDto(@NotNull(message = "Body Type id can't be null") Long bodyTypeId) {
}
