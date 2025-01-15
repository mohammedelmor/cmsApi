package org.mohammed.cmsapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mohammed.cmsapi.dto.MuscleBalanceGetDto;
import org.mohammed.cmsapi.model.MuscleBalance;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MuscleBalanceMapper {

    MuscleBalanceGetDto toDto(MuscleBalance muscleBalance);
}