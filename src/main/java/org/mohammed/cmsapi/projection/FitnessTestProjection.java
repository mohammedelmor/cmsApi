package org.mohammed.cmsapi.projection;

import org.mohammed.cmsapi.dto.BodyTypeGetDto;
import org.mohammed.cmsapi.dto.MuscleBalanceGetDto;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.Set;

public interface FitnessTestProjection {

    Long getId();
    String getExercise();
    Integer getRepetition();
    Integer getDuration();
    String getComment();
    TraineeProjection getTrainee();

    interface TraineeProjection {
        Long getId();
        String getName();
        Integer getAge();
        String getPhoneNumber();
        BodyTypeGetDto getBodyType();
        MuscleBalanceGetDto getMuscleBalance();
        Set<String> getQuestionnaireChecks();
        LocalDateTime getCreatedDate();
        LocalDateTime getLastModifiedDate();
    }
}
