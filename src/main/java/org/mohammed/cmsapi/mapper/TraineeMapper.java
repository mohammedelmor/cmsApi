package org.mohammed.cmsapi.mapper;

import org.mapstruct.*;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.model.Trainee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TraineeMapper {
    Trainee toEntity(TraineePostDto traineePostDto);

    TraineeGetDto toDto(Trainee trainee);
}