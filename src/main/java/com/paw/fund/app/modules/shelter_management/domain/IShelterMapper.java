package com.paw.fund.app.modules.shelter_management.domain;

import com.paw.fund.app.modules.shelter_management.repository.database.ShelterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IShelterMapper {
    ShelterEntity toEntity(Shelter dto);

    Shelter toDto(ShelterEntity entity);

    void update(@MappingTarget ShelterEntity entity, Shelter shelter);
}
