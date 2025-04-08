package com.paw.fund.app.modules.license_management.controller.models;

import com.paw.fund.app.modules.license_management.domain.License;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILicenseModelMapper {
    License toDto(LicenseRequest request);

    LicenseResponse toResponse(License dto);
}
