package org.mohammed.cmsapi.mapper;

import org.mapstruct.*;
import org.mohammed.cmsapi.dto.TraineeGetDto;
import org.mohammed.cmsapi.dto.TraineePostDto;
import org.mohammed.cmsapi.model.Trainee;
import org.mohammed.cmsapi.model.embeddable.QuestionnaireCheck;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {BodyTypeMapper.class, MuscleBalanceMapper.class}
)
public interface TraineeMapper {
    Trainee toEntity(TraineePostDto traineePostDto);

    @Mapping(source = "questionnaireChecks", target = "questionnaireChecks", qualifiedByName = "mapToCheckNames")
    TraineeGetDto toDto(Trainee trainee);

    @Named("mapToCheckNames")
    default Set<String> mapToCheckNames(Set<QuestionnaireCheck> checks) {
        if (checks == null) {
            return null;
        }
        return checks.stream()
                .map(QuestionnaireCheck::getCheckName)
                .collect(Collectors.toSet());
    }
}