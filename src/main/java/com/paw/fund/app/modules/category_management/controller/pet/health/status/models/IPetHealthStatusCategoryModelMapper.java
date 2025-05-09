package com.paw.fund.app.modules.category_management.controller.pet.health.status.models;

import com.paw.fund.app.modules.category_management.domain.PetHealthStatusCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPetHealthStatusCategoryModelMapper {
    PetHealthStatusCategoryResponse toResponse(PetHealthStatusCategory dto);
}
