package com.paw.fund.app.modules.category_management.controller.shelter.models;

import com.paw.fund.app.modules.category_management.domain.ShelterRegistrationStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IShelterRegistrationStatusCategoryModelMapper {
    ShelterRegistrationStatusCategoryResponse toResponse(ShelterRegistrationStatusCategory dto);
}
