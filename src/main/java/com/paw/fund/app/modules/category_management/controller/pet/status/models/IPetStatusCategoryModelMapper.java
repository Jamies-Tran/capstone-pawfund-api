package com.paw.fund.app.modules.category_management.controller.pet.status.models;

import com.paw.fund.app.modules.category_management.domain.PetStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetStatusCategoryModelMapper {
    PetStatusCategoryResponse toResponse(PetStatusCategory dto);
}
