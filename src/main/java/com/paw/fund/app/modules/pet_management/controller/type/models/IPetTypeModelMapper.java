package com.paw.fund.app.modules.pet_management.controller.type.models;

import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetTypeModelMapper {
    PetType toDto(PetTypeRequest request);

    PetTypeResponse toResponse(PetType dto);
}
