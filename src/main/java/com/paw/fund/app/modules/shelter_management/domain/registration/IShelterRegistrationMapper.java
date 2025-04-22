package com.paw.fund.app.modules.shelter_management.domain.registration;

import com.paw.fund.app.modules.shelter_management.repository.database.registration.ShelterRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IShelterRegistrationMapper {
    ShelterRegistrationEntity toEntity(ShelterRegistration dto);

    ShelterRegistration toDto(ShelterRegistrationEntity entity);
}
