package com.paw.fund.app.modules.category_management.controller.pet.color.models;

import com.paw.fund.app.modules.category_management.domain.PetColorCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetColorCategoryModelMapper {
    PetColorCategoryResponse toResponse(PetColorCategory dto);
}
