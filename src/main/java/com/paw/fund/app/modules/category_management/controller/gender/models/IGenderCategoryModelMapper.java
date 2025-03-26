package com.paw.fund.app.modules.category_management.controller.gender.models;

import com.paw.fund.app.modules.category_management.domain.GenderCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IGenderCategoryModelMapper {
    GenderCategoryResponse toResponse(GenderCategory dto);
}
