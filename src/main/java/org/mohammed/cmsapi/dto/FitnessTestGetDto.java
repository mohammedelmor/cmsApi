package org.mohammed.cmsapi.dto;

public record FitnessTestGetDto(Long id,
                                String exercise,
                                Integer weight,
                                Integer repetition,
                                Integer duration,
                                String comment,
                                TraineeGetDto trainee) {
}
