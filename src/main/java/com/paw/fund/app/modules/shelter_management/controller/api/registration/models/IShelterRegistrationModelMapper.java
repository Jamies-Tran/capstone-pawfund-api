package com.paw.fund.app.modules.shelter_management.controller.api.registration.models;

import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IShelterRegistrationModelMapper {
    ShelterRegistrationResponse toResponse(ShelterRegistration dto);

    Shelter toDto(ShelterRegistrationRequest request);

    ShelterResponse toResponse(Shelter dto);
}
