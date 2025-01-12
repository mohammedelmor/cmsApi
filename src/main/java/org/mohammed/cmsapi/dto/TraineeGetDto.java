package org.mohammed.cmsapi.dto;

import java.io.Serializable;

/**
 * DTO for {@link org.mohammed.cmsapi.model.Trainee}
 */
public record TraineeGetDto(Long id, String name, Integer age) implements Serializable {
}