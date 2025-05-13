package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetIntakeRegistrationModelMapper {
    PetIntakeRegistration toDto(PetIntakeRegistrationRequest request);

    PetIntakeRegistrationResponse toResponse(PetIntakeRegistration dto);

    PetIntakeRegistration toDto(PetIntakeRegistrationUpdateRequest request);
}
