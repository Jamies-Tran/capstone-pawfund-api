package com.paw.fund.app.modules.category_management.controller.license.models.type;

import com.paw.fund.app.modules.category_management.domain.LicenseTypeCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILicenseTypeCategoryModelMapper {
    LicenseTypeCategoryResponse toResponse(LicenseTypeCategory dto);
}
