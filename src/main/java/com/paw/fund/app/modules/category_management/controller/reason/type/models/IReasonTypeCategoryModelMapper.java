package com.paw.fund.app.modules.category_management.controller.reason.type.models;

import com.paw.fund.app.modules.category_management.domain.ReasonTypeCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IReasonTypeCategoryModelMapper {
    ReasonTypeCategoryResponse toResponse(ReasonTypeCategory dto);
}
