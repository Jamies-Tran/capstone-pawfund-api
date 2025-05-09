package com.paw.fund.app.modules.category_management.controller.pet.information.status.models;

import com.paw.fund.app.modules.category_management.domain.PetInformationStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetInformationStatusCategoryModelMapper {
    PetInformationStatusCategoryResponse toResponse(PetInformationStatusCategory dto);
}
