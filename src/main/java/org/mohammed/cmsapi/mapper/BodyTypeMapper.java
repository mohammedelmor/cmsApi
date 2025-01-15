package org.mohammed.cmsapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mohammed.cmsapi.dto.BodyTypeGetDto;
import org.mohammed.cmsapi.model.BodyType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BodyTypeMapper {
    
    BodyTypeGetDto toDto(BodyType muscleBalance);
}