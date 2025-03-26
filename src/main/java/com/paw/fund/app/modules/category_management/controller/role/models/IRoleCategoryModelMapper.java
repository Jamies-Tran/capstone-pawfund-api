package com.paw.fund.app.modules.category_management.controller.role.models;

import com.paw.fund.app.modules.category_management.domain.RoleCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleCategoryModelMapper {
    RoleCategoryResponse toResponse(RoleCategory roleCategory);
}
