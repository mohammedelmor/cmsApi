package org.mohammed.cmsapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.cmsapi.model.Trainee}
 */
public record TraineePutDto(@NotNull(message = "A Trainee must have a name") String name,
                            @NotNull(message = "A Trainee must have an age") @Min(value = 5, message = "Age less than 5?") Integer age,
                            String phoneNumber) implements Serializable {
}