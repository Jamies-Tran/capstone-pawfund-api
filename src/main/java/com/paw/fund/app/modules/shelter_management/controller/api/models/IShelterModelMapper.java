package com.paw.fund.app.modules.shelter_management.controller.api.models;

import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IShelterModelMapper {
    ShelterResponse toResponse(Shelter dto);
}
