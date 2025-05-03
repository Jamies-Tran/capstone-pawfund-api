package com.paw.fund.app.modules.pet_management.controller.pet.models;

import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetModelMapper {
    Pet toDto(PetRequest request);

    Pet toDto(PetUpdateRequest request);

    PetResponse toResponse(Pet dto);
}
