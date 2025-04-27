package com.paw.fund.app.modules.pet_management.controller.breed.models;

import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetBreedModelMapper {
    PetBreed toDto(PetBreedRequest request);

    PetBreedResponse toResponse(PetBreed dto);
}
