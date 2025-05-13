package com.paw.fund.app.modules.pet_intake_registration_management.domain;

import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.PetIntakeRegistrationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetIntakeRegistrationMapper {
    PetIntakeRegistration toDto(PetIntakeRegistrationEntity entity);

    PetIntakeRegistrationEntity toEntity(PetIntakeRegistration dto);

    void update(@MappingTarget PetIntakeRegistrationEntity entity, PetIntakeRegistration dto);
}
