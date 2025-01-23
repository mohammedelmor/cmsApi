package org.mohammed.cmsapi.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FitnessTestPostDto(@NotBlank(message = "Exercise can't be empty") String exercise,
                                 @Min(value = 1, message = "weight can't be less than 1") Integer weight,
                                 @Min(value = 1, message = "repetition can't be less than 1") Integer repetition,
                                 @Min(value = 1, message = "duration can't be less than 1")Integer duration,
                                 String comment,
                                 @NotNull(message = "trainee id must be set") Long traineeId) {
}
