package com.paw.fund.app.modules.category_management.controller.shelter.assignment.status.models;

import com.paw.fund.app.modules.category_management.domain.ShelterAssignmentStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IShelterAssignmentStatusCategoryModelMapper {
    ShelterAssignmentStatusCategoryResponse toResponse(ShelterAssignmentStatusCategory dto);
}
