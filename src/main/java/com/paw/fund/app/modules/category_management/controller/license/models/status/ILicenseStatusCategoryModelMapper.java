package com.paw.fund.app.modules.category_management.controller.license.models.status;

import com.paw.fund.app.modules.category_management.domain.LicenseStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILicenseStatusCategoryModelMapper {
    LicenseStatusCategoryResponse toResponse(LicenseStatusCategory dto);
}
