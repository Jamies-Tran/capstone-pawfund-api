package com.paw.fund.app.modules.category_management.controller.pet.gender.models;

import com.paw.fund.app.modules.category_management.domain.PetGenderCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetGenderCategoryModelMapper {
    PetGenderCategoryResponse toResponse(PetGenderCategory dto);
}
