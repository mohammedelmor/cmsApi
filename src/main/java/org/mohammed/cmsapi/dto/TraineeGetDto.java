package org.mohammed.cmsapi.dto;

import org.mohammed.cmsapi.model.embeddable.QuestionnaireCheck;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link org.mohammed.cmsapi.model.Trainee}
 */
public record TraineeGetDto(Long id,
                            String name,
                            Integer age,
                            String phoneNumber,
                            BodyTypeGetDto bodyType,
                            MuscleBalanceGetDto muscleBalance,
                            Set<String> questionnaireChecks,
                            LocalDateTime createdDate,
                            LocalDateTime lastModifiedDate) implements Serializable {
}