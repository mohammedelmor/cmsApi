package org.mohammed.cmsapi.mapper;

import org.mapstruct.*;
import org.mohammed.cmsapi.dto.FitnessTestGetDto;
import org.mohammed.cmsapi.dto.FitnessTestPostDto;
import org.mohammed.cmsapi.model.FitnessTest;
import org.mohammed.cmsapi.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TraineeMapper.class}
)
public abstract class FitnessTestMapper {

    @Autowired
    protected TraineeService traineeService;

    @Mapping(target = "trainee", expression = "java(traineeService.getById(dto.traineeId()))")
    public abstract FitnessTest toEntity(FitnessTestPostDto dto);

    public abstract FitnessTestGetDto toDto(FitnessTest fitnessTest);

}
