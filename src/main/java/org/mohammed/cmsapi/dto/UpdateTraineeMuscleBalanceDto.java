package org.mohammed.cmsapi.dto;


import jakarta.validation.constraints.NotNull;

public record UpdateTraineeMuscleBalanceDto(@NotNull(message = "Muscle Balance id can't be null") Long muscleBalanceId){
}
