package org.mohammed.cmsapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.mohammed.cmsapi.model.Trainee}
 */
public record TraineeGetDto(Long id, String name, Integer age, String phoneNumber,LocalDateTime createdDate, LocalDateTime lastModifiedDate) implements Serializable {
}