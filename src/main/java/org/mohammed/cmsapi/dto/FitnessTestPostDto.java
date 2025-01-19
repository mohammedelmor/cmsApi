package org.mohammed.cmsapi.dto;


public record FitnessTestPostDto(String exercise,
                                 Integer repetition,
                                 Integer duration,
                                 String comment,
                                 Long traineeId) {
}
