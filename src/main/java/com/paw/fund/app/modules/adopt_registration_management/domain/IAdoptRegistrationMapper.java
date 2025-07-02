package com.paw.fund.app.modules.adopt_registration_management.domain;

import com.paw.fund.app.modules.adopt_registration_management.repository.database.AdoptRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAdoptRegistrationMapper {
    AdoptRegistration toDto(AdoptRegistrationEntity entity);

    AdoptRegistrationEntity toEntity(AdoptRegistration dto);

    void update(@MappingTarget AdoptRegistrationEntity entity, AdoptRegistration dto);
}
